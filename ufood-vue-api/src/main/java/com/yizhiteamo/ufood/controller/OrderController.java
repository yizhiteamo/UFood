package com.yizhiteamo.ufood.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.yizhiteamo.ufood.entity.Order;
import com.yizhiteamo.ufood.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Ralts
 */
@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderService orderService;


    /**
     *
     * 分页查询用户数据
     * 根据用户名模糊查询
     * offset 为页面偏移量
     */
    @GetMapping("/findByPage")
    public IPage<Order> findByPage(@RequestParam Integer pageNum,
                                   @RequestParam Integer pageSize,
                                   @RequestParam(defaultValue = "") String userInfo,
                                   @RequestParam(defaultValue = "") String orderContent,
                                   @RequestParam(defaultValue = "") String orderTime) {
        return orderService.findByPage(pageNum, pageSize, userInfo, orderContent, orderTime);
    }

    /**
     *  新增 或 修改 订单
     */
    @PostMapping("/saveOrUpdate")
    public boolean saveOrUpdate(@RequestBody Order goods){
        return orderService.saveOrUpdateOrder(goods);
    }

    /**
     *  删除 订单
     */
    @DeleteMapping("/delOrderById/{id}")
    public boolean delOrderById(@PathVariable("id") Integer id) {
        return orderService.delOrderByID(id);
    }

    /**
     *  批量 删除 订单
     */
    @PostMapping("/delOrderByBatch")
    public boolean delOrderByBatch(@RequestBody List<Integer> ids){
        return orderService.delOrderByBatch(ids);
    }
}
