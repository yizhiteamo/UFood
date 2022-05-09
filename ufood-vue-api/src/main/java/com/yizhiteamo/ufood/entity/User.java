package com.yizhiteamo.ufood.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import org.springframework.stereotype.Repository;

import java.util.Date;


/**
 * @author Ralts
 * @description 用户实体类
 */
@Data
@TableName(value = "ufood_user")
@Repository
public class User {
    /**
     * 主键user_id，用户名，密码（存错加密密文），盐（加密盐），性别，真实姓名，生日，头像，邮箱，角色id, 用户状态
     */
    @TableId(value = "uid", type = IdType.AUTO)
    private Integer uid;

    @TableField(value = "username")
    private String username;

    @TableField(value = "password")
    private String password;

    @TableField(value = "gender")
    private int gender;

    @TableField(value = "phone")
    private String phone;

    @TableField(value = "address")
    private String address;

    /**
     * 返回时间格式
     */
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    @TableField(value = "birthday")
    private Date birthday;

    @TableField(value = "avatar_url")
    private String avatar;

    @TableField(value = "email")
    private String email;


    @TableField(exist = false)
    private String code;


}


