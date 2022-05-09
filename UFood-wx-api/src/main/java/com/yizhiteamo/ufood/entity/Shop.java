package com.yizhiteamo.ufood.entity;

import lombok.Data;

@Data
public class Shop {
    private Integer shop_no;
    private String introduce;
    private String location;
    private String brief;
    private String name;
    private String category;
    private String shop_icon;
    private Boolean popular;
}
