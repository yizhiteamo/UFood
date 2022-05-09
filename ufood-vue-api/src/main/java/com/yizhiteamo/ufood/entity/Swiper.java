package com.yizhiteamo.ufood.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @author Ralts
 */
@Data
@TableName(value = "ufood_swiper")
public class Swiper {
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    @TableField(value = "swiper_url")
    private String swiperUrl;
    @TableField(value = "goods_no")
    private String goodsNo;
    @TableField(value = "index_swiper")
    private boolean indexSwiper;
}
