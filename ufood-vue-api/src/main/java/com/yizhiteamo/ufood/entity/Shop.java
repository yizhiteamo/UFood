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
@TableName(value = "ufood_shop")
public class Shop {
    @TableId(value = "shop_no", type = IdType.AUTO)
    private Integer shopNo;
    @TableField(value = "location")
    private String location;
    @TableField(value = "brief")
    private String brief;
    @TableField(value = "name")
    private String name;
    @TableField(value = "category")
    private String category;
    @TableField(value = "shop_icon")
    private String shopIcon;
    @TableField(value = "popular")
    private Boolean popular;
    @TableField(value = "createTime")
    private Date createTime;
}
