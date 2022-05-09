package com.yizhiteamo.ufood.service;

import com.yizhiteamo.ufood.entity.Order;
import com.yizhiteamo.ufood.mapper.OrderMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class OrderService {

    @Resource
    private OrderMapper orderMapper;

    /**
     * 存储用户订单信息
     * @param orderTime     订单 预定日期
     * @param userInfo      订单的用户信息
     * @param orderContent  订单内容
     */
    public void setOrder(String orderTime, String userInfo,String orderContent){
        orderMapper.setOrder(orderTime, userInfo, orderContent);
    }

    /**
     * @param userInfo 用户信息
     * @return 对应的订单信息
     */
    public List<Order> findOrderByUser(String userInfo) {
        return orderMapper.findOrderByUser(userInfo);
    }

    /**
     *
     */
    public void orderRefund(Integer id){
        orderMapper.orderRefund(id);
    }

}
