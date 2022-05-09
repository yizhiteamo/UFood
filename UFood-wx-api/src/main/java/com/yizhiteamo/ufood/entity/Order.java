package com.yizhiteamo.ufood.entity;

import lombok.Data;

/**
 * @author Ralts
 */
@Data
public class Order {
    private Integer id;
    private String orderTime;
    private String userInfo;
    private String orderContent;
    private String createTime;
    private String status;
}
