package com.yizhiteamo.ufood.mapper;

import com.yizhiteamo.ufood.entity.Order;

import java.util.List;

public interface OrderMapper {

    /**
     * 存储用户订单信息
     * @param orderTime     订单 预定日期
     * @param userInfo      订单的用户信息
     * @param orderContent  订单内容
     */
    void setOrder(String orderTime, String userInfo,String orderContent);

    /**
     * 获取对应用户的订单信息
     * @param userInfo 用户信息
     * @return 对应的订单信息
     */
    List<Order> findOrderByUser(String userInfo);

    /**
     * 获取对应用户的订单信息
     * @param id 订单编号
     */
    void orderRefund(Integer id);

}
