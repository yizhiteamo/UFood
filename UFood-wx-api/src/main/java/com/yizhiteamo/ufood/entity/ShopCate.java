package com.yizhiteamo.ufood.entity;

import lombok.Data;

import java.util.List;

@Data
public class ShopCate {
    private Integer id;
    private String category;
    private List<Shop> shops;

}
