package com.yizhiteamo.ufood.controller;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yizhiteamo.ufood.common.Constants;
import com.yizhiteamo.ufood.common.Result;
import com.yizhiteamo.ufood.common.RetrieveResult;
import com.yizhiteamo.ufood.common.RetrieveResultFactory;
import com.yizhiteamo.ufood.entity.User;
import com.yizhiteamo.ufood.entity.dto.UserDTO;
import com.yizhiteamo.ufood.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Ralts
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * 用户登录操作
     */
    @PostMapping("/login")
    public Result login(@RequestBody UserDTO userDTO) {
        String username = userDTO.getUsername();
        String password = userDTO.getPassword();
        if(StrUtil.isBlank(username) || StrUtil.isBlank(password)) {
            return Result.error(Constants.CODE_400, "参数错误");
        }
        UserDTO dto = userService.login(userDTO);
        return Result.success(dto);
    }

    /**
     * 用户注册
     */
    @PostMapping("/register")
    public Result register(@RequestBody UserDTO userDTO) {
        String username = userDTO.getUsername();
        String password = userDTO.getPassword();
        if(StrUtil.isBlank(username) || StrUtil.isBlank(password)) {
            return Result.error(Constants.CODE_400, "参数错误");
        }
        return Result.success(userService.register(userDTO));
    }

    /**
     * 分页查询用户数据
     * 根据用户名模糊查询
     * offset 为页面偏移量
     */
    @GetMapping("/findByPage")
    public Result findByPage(@RequestParam(defaultValue = "1") Integer pageNum,
                                  @RequestParam(defaultValue = "5") Integer pageSize,
                                  @RequestParam(defaultValue = "") String username,
                                  @RequestParam(defaultValue = "") String email,
                                  @RequestParam(defaultValue = "") String address) {
        return Result.success(userService.findByPage(pageNum, pageSize, username, email, address));
    }

    /**
     * 根据用户名查询用户信息
     */
    @GetMapping("/getUserByName/{username}")
    public Result getUserByName(@PathVariable("username") String username) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", username);
        return Result.success(userService.getOne(queryWrapper));
    }

    /**
     *  新增 或 修改 用户
     */
    @PostMapping("/saveOrUpdateUser")
    public Result saveOrUpdateUser(@RequestBody User user){
        return Result.success(userService.saveOrUpdateUser(user));
    }

    /**
     *  删除 用户
     */
    @DeleteMapping("/delUserById/{id}")
    public Result delUserById(@PathVariable("id") Integer id) {
        return Result.success(userService.delUserByID(id));
    }

    /**
     *  批量 删除 用户
     */
    @PostMapping("/delUserByBatch")
    public Result delUserByBatch(@RequestBody List<Integer> ids){
        return Result.success(userService.delUserByBatch(ids));
    }

    /**
     * 找回密码 第一步
     * @param user 用户信息
     * @return
     */
    @PostMapping("/retrieve/step1")
    public RetrieveResult retrievePasswordOne(@RequestBody User user) {
        if (user.getUsername()!=null&& user.getEmail()!=null){
            //第一步，传来的是用户名和邮件，其他为空，则生成验证码并发送邮件
            return userService.checkUserAndEmail(user);
        }
        return RetrieveResultFactory.buildFailResult("未知错误");
    }

    /**
     * 找回密码 第二步
     * @param user 用户信息
     * @return
     */
    @PostMapping("/retrieve/step2")
    public RetrieveResult retrievePasswordTwo(@RequestBody User user) {
        if (user.getCode()!=null && user.getUsername()!=null) {
            //第二步，传来code和username，需要验证数据库中的code是否正确
            return userService.checkCode(user.getCode(), user.getUsername());
        }
        return RetrieveResultFactory.buildFailResult("未知错误");
    }

    /**
     * 找回密码 第三步
     * @param user 用户信息
     * @return
     */
    @PostMapping("/retrieve/step3")
    public RetrieveResult retrievePasswordThree(@RequestBody User user) {
        if (user.getUsername()!=null && user.getPassword()!=null) {
            //最后，用户名和密码同时传过来，开始重置密码。
            return userService.resetPassword(user.getUsername(), user.getPassword());
        }
        return RetrieveResultFactory.buildFailResult("未知错误");
    }

}
