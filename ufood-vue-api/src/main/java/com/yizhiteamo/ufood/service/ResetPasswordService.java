package com.yizhiteamo.ufood.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yizhiteamo.ufood.entity.ResetPassword;
import com.yizhiteamo.ufood.mapper.ResetPasswordMapper;

import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author Ralts
 * @description 重置密码的实现类
 */
@Service
public class ResetPasswordService extends ServiceImpl<ResetPasswordMapper, ResetPassword> {

    @Resource
    private ResetPasswordService resetPasswordService;

    /**
     * 判断是否存在该用户修改的数据，
     * @param uid 用户id
     * @return boolean
     */
    public boolean isExistUserId(int uid) {
        QueryWrapper<ResetPassword> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("uid", uid);
        return resetPasswordService.count(queryWrapper) > 0;
    }

    /**
     * 通过用户id获取这条数据
     * @param uid 用户id
     * @return ResetPassword实例
     */
    public ResetPassword getOneByUserId(int uid) {
        QueryWrapper<ResetPassword> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("uid", uid);
        return resetPasswordService.getOne(queryWrapper);
    }
}


