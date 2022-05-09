package com.yizhiteamo.ufood.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yizhiteamo.ufood.entity.Order;
import com.yizhiteamo.ufood.mapper.OrderMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @author Ralts
 */
@Service
public class OrderService extends ServiceImpl<OrderMapper, Order> {

    /**
     * 新增或修改数据
     */
    public boolean saveOrUpdateOrder(Order order) {
        return saveOrUpdate(order);
    }

    /**
     *  删除 订单
     */
    public boolean delOrderByID(Integer id){
        return removeById(id);
    }

    /**
     *  批量 删除 订单
     */
    public boolean delOrderByBatch(List<Integer> ids){
        return removeBatchByIds(ids);
    }


    /**
     * 分页查询订单数据
     * 根据 用户信息 预约时间 订单内容 订单编号 模糊查询
     */
    public IPage<Order> findByPage(@RequestParam Integer pageNum,
                                  @RequestParam Integer pageSize,
                                  @RequestParam(defaultValue = "") String userInfo,
                                  @RequestParam(defaultValue = "") String orderContent,
                                  @RequestParam(defaultValue = "") String orderTime){
        IPage<Order> page = new Page<>(pageNum, pageSize);
        QueryWrapper<Order> queryWrapper = new QueryWrapper<>();
        if (!"".equals(userInfo)) {
            queryWrapper.like("userInfo", userInfo);
        }
        if (!"".equals(orderContent)) {
            queryWrapper.like("orderContent", orderContent);
        }
        if (!"".equals(orderTime)) {
            queryWrapper.like("orderTime", orderTime);
        }
        queryWrapper.orderByDesc("createTime");
        return page(page, queryWrapper);
    }

}
