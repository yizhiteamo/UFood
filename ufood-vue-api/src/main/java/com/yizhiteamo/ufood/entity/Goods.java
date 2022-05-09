package com.yizhiteamo.ufood.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.List;

/**
 * @author Ralts
 */
@Data
@TableName(value = "ufood_goods")
public class Goods {
    @TableId(value = "goods_no",type = IdType.AUTO)
    private Integer goodsNo;
    @TableField(value = "name")
    private String name;
    @TableField(value = "price")
    private Integer price;
    @TableField(value = "pic_url")
    private String picUrl;
    @TableField(value = "shop_no")
    private String shopNo;
    @TableField(value = "deleted")
    private boolean deleted;
    @TableField(value = "collected")
    private boolean collected;
    @TableField(value = "cart")
    private boolean cart;
    @TableField(value = "introduce")
    private String introduce;
}
