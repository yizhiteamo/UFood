package com.yizhiteamo.ufood.controller;

import com.yizhiteamo.ufood.entity.Order;
import com.yizhiteamo.ufood.service.OrderService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Resource
    private OrderService orderService;

    /**
     * 存储订单信息
     */
    @PostMapping("/setOrder")
    public void setOrder(@RequestParam String orderTime,
                           @RequestParam String userInfo,
                           @RequestParam String orderContent) {
        orderService.setOrder(orderTime, userInfo, orderContent);
    }

    /**
     * @param userInfo 用户信息
     * @return 对应的订单信息
     */
    @GetMapping("/findOrderByUser")
    public List<Order> findOrderByUser(String userInfo) {
        return orderService.findOrderByUser(userInfo);
    }

    @PostMapping("/orderRefund/{id}")
    public void orderRefund(@PathVariable("id") Integer id) {
        orderService.orderRefund(id);
    }

}
