package com.yizhiteamo.ufood.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

/**
 * @author Ralts
 */
@Data
@TableName(value = "ufood_order")
public class Order {
    @TableId(value = "id",type = IdType.AUTO)
    private Integer id;
    @TableField(value = "orderTime")
    private Date orderTime;
    @TableField(value = "userInfo")
    private String userInfo;
    @TableField(value = "orderContent")
    private String orderContent;
    @TableField(value = "createTime")
    private Date createTime;
    private String status;
}
