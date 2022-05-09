package com.yizhiteamo.ufood.service;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.log.Log;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yizhiteamo.ufood.common.Constants;
import com.yizhiteamo.ufood.common.RetrieveResult;
import com.yizhiteamo.ufood.common.RetrieveResultFactory;
import com.yizhiteamo.ufood.entity.ResetPassword;
import com.yizhiteamo.ufood.entity.User;
import com.yizhiteamo.ufood.entity.dto.UserDTO;
import com.yizhiteamo.ufood.exception.ServiceException;
import com.yizhiteamo.ufood.mapper.UserMapper;
import com.yizhiteamo.ufood.utils.MD5Util;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.util.HtmlUtils;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;


/**
 * @author Ralts
 */
@Service
public class UserService extends ServiceImpl<UserMapper, User>{

    @Resource
    private UserMapper userMapper;

    private static final Log LOG = Log.get();

    /**
     * 用户登录操作
     */
    public UserDTO login(UserDTO userDTO) {
        userDTO.setPassword(MD5Util.inputPassToFormPass(userDTO.getPassword()));
        User one = getUserInfo(userDTO);
        if(one != null) {
            BeanUtil.copyProperties(one, userDTO, true);
            return userDTO;
        } else {
            throw new ServiceException(Constants.CODE_600, "用户名或密码错误");
        }
    }

    /**
     * 封装查询用户信息代码
     */
    private User getUserInfo(UserDTO userDTO) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", userDTO.getUsername());
        queryWrapper.eq("password", userDTO.getPassword());
        User one;
        try {
            // 从数据库中查询用户信息
            one = getOne(queryWrapper);
        } catch (Exception e) {
            LOG.error(e);
            throw new ServiceException(Constants.CODE_500, "系统错误");
        }
        return one;
    }

    /**
     * 用户注册操作
     */
    public User register(UserDTO userDTO) {
        userDTO.setPassword(MD5Util.inputPassToFormPass(userDTO.getPassword()));
        User one = getUserInfo(userDTO);
        if (one == null) {
            one = new User();
            one.setPassword(MD5Util.inputPassToFormPass(one.getPassword()));
            BeanUtil.copyProperties(userDTO, one, true);
            // 存储用户对象
            save(one);
        } else {
            throw new ServiceException(Constants.CODE_600, "用户已存在");
        }
        return one;
    }

    /**
     * 新增或修改数据
     */
    public boolean saveOrUpdateUser(User user) {
        return saveOrUpdate(user);
    }

    /**
     *  删除 用户
     */
    public boolean delUserByID(Integer id){
        return removeById(id);
    }

    /**
     *  批量 删除 用户
     */
    public boolean delUserByBatch(List<Integer> ids){
        return removeBatchByIds(ids);
    }


    /**
     * 分页查询用户数据
     * 根据 用户名 邮箱 地址 模糊查询
     */
    public IPage<User> findByPage(@RequestParam Integer pageNum,
                                  @RequestParam Integer pageSize,
                                  @RequestParam(defaultValue = "") String username,
                                  @RequestParam(defaultValue = "") String email,
                                  @RequestParam(defaultValue = "") String address) {
        IPage<User> page = new Page<>(pageNum, pageSize);
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        if (!"".equals(username)) {
            queryWrapper.like("username", username);
        }
        if (!"".equals(email)) {
            queryWrapper.like("email", email);
        }
        if (!"".equals(address)) {
            queryWrapper.like("address", address);
        }
        queryWrapper.orderByDesc("create_time");
        return page(page, queryWrapper);
    }


    @Resource
    private UserService userService;

    @Resource
    private ResetPasswordService resetPasswordService;

    @Resource
    private EmailService emailService;

    /**
     * 检查想重置密码的用户和邮箱是否匹配，若匹配成功，则返回当前用户的相关信息
     * @param user 用户实体
     * @return Result信息
     */
    public RetrieveResult checkUserAndEmail(User user) {
        //获取前端传来的数据
        String username = user.getUsername();
        String email = user.getEmail();
        //将用户名中可能存在HTML编码转义
        username = HtmlUtils.htmlEscape(username);
        email = HtmlUtils.htmlEscape(email);
        if (isExistUser(username)) {
            //如果存在该用户，则查找该用户的邮箱及用户id
            String storeEmail = findEmailByUsername(username);
            int uid = findIdByUsername(username);
            if (email.equals(storeEmail)) {
                //用户名和邮箱匹配正确后，需要在表resetPassword中生成一条信息，保存验证码，修改次数等信息
                //生成一个6位的数字
                int num = (int) ((Math.random() * 9 + 1) * 100000);
                String code = String.valueOf(num);
                //当前时间作为修改密码的开始时间
                Date currentTime = new Date();
                //截至时间为开始时间延后5分钟
                Date deadline = new Date(currentTime.getTime() + 5*60*1000);
                int limitNum = 3;
                int isEffective = 1;
                if (resetPasswordService.isExistUserId(uid)) {
                    //如果表中存在这一个用户的修改信息，那么只需要修改相关信息
                    ResetPassword rp = resetPasswordService.getOneByUserId(uid);
                    if (currentTime.after(new Date(rp.getCreateTime().getTime() + 24*60*60*1000))){
                        //如果当前的时间在修改密码存储时间后一天，那么就判断过了冷却时间，该用户可以重新设置密码, 24小时60分钟60s
                        rp.setIsEffective(isEffective);
                        rp.setResetNum(0);
                    }
                    if (rp.getResetNum() > rp.getLimitNum()) {
                        //如果当前修改次数超过上限
                        rp.setIsEffective(0);
                    }
                    //先判断用户是否能够修改密码, 值为1代表允许，否则不能修改
                    if (rp.getIsEffective() == 1) {
                        // 设置对应的数据
                        rp.setCode(code);
                        rp.setCreateTime(currentTime);
                        rp.setDeadline(deadline);
                        //重置次数+1
                        int resetTimes = rp.getResetNum();
                        resetTimes++;
                        rp.setResetNum(resetTimes);
                        rp.setLimitNum(limitNum);
                        if (rp.getResetNum() > rp.getLimitNum()) {
                            // 修改次数 > 限制次数
                            rp.setIsEffective(0);
                        }else {
                            rp.setIsEffective(isEffective);
                        }
                    }else {
                        return RetrieveResultFactory.buildFailResult("当日账号密码修改次数超过上限，请明天重试！");
                    }
                    try{
                        resetPasswordService.updateById(rp);
                    }catch (Exception e) {
                        System.out.println(e);
                    }
                }else {
                    //表中不存在这个用户，那么需要重新添加一条新数据
                    ResetPassword rp = new ResetPassword();
                    rp.setUid(uid);
                    rp.setCode(code);
                    rp.setCreateTime(currentTime);
                    rp.setDeadline(deadline);
                    rp.setIsEffective(isEffective);
                    rp.setResetNum(0);
                    rp.setLimitNum(limitNum);
                    try{
                        resetPasswordService.save(rp);
                    }catch (Exception e) {
                        System.out.println(e);
                    }
                }
                //发送html邮件到对应的邮箱号
                String title = "重置密码-来自食优后台管理平台";
                String content = "<html>\n" +
                        "<body>\n" +
                        "<h3>hello! 忘记密码啦？!</h3>\n" +
                        "<p>" + "用户" + username + ": 你好"+"<br/>" + "你正在食优后台管理平台进行重置密码操作<br/>" +
                        "您本次重置密码的验证码为<br/>" +
                        "<p style=\"font-size:24px; color: #409EFF\">" + code + "</p>" +
                        "<br/>请在5分钟之内填写验证码"+
                        "<br/>如果非本人操作，请忽略本邮件, 如有疑问，欢迎致信541361418@qq.com" +
                        "</P>" +
                        "</body>\n" +
                        "</html>\n";
                //发送Html邮件时间相对较长
                emailService.sendHtmlMail(email, title, content);
                return RetrieveResultFactory.buildSuccessResult(username);
            }
            return RetrieveResultFactory.buildFailResult("用户邮箱号输入错误，请重新输入");
        }
        return RetrieveResultFactory.buildFailResult("该用户未注册，请先注册账号");
    }

    /**
     * 检查验证码是否正确
     * @param code 验证码
     * @param username 用户username
     * @return Result信息
     */
    public RetrieveResult checkCode(String code, String username) {
        int uid = findIdByUsername(username);
        ResetPassword rp = resetPasswordService.getOneByUserId(uid);
        //获取当前时间
        Date currentTime = new Date();
        if (currentTime.after(rp.getDeadline())) {
            return RetrieveResultFactory.buildFailResult("验证时间已过,请刷新界面,从头开始重置密码！");
        }else {
            if (rp.getCode().equals(code)) {
                return RetrieveResultFactory.buildSuccessResult("验证码匹配正确", username);
            }
            return RetrieveResultFactory.buildFailResult("验证码匹配错误!");
        }
    }

    /**
     * 重置密码
     * @param password 输入密码的md5密文
     * @param username 用户名
     * @return Result信息
     */
    public RetrieveResult resetPassword(String username, String password) {
        int uid = findIdByUsername(username);
        try {
            User user = userService.getById(uid);
            // 密码进行MD5加密
            user.setPassword(MD5Util.inputPassToFormPass(password));
            // 更新到数据库
            userService.updateById(user);
        }catch (Exception e) {
            e.printStackTrace();
            return RetrieveResultFactory.buildFailResult("未知错误");
        }
        return RetrieveResultFactory.buildSuccessResult("修改密码成功");
    }

    /**
     * 判断数据库中是否存在用户
     * @param username 用户名
     * @return true、false
     */
    public boolean isExistUser(String username) {
        //mybatis-plus的条件构造器queryWrapper
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", username);
        return userService.count(queryWrapper) > 0;
    }

    /**
     * 通过用户名查找对应的邮箱号
     * @param username 用户名
     * @return email 邮箱号
     */
    public String findEmailByUsername(String username) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", username);
        //找到数据库中用户名和输入用户名相同的一条数据
        User user = userService.getOne(queryWrapper);
        //返回对应的邮箱
        return user.getEmail();
    }

    /**
     * 通过用户名查找对应的用户id
     * @param username 用户名
     * @return id
     */
    public int findIdByUsername(String username) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", username);
        //找到数据库中用户名和输入用户名相同的一条数据
        User user = userService.getOne(queryWrapper);
        //返回对应的用户id
        return user.getUid();
    }

}
