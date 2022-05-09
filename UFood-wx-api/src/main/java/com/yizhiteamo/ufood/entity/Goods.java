package com.yizhiteamo.ufood.entity;

import lombok.Data;

import java.util.List;

@Data
public class Goods {
    private Integer goods_no;
    private String name;
    private Integer price;
    private String pic_url;
    private Integer shop_no;
    private boolean deleted;
    private boolean collected;
    private boolean cart;
    private String introduce;

    private List<Swiper> swiperList;
}
