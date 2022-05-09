package com.yizhiteamo.ufood.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yizhiteamo.ufood.entity.Shop;
import com.yizhiteamo.ufood.mapper.ShopMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @author Ralts
 */
@Service
public class ShopService extends ServiceImpl<ShopMapper, Shop> {

    /**
     * 新增或修改数据
     */
    public boolean saveOrUpdateShop(Shop shop) {
        return saveOrUpdate(shop);
    }

    /**
     *  删除 店铺
     */
    public boolean delShopByID(Integer shop_no){
        return removeById(shop_no);
    }

    /**
     *  批量 删除 店铺
     */
    public boolean delShopByBatch(List<Integer> shop_nos){
        return removeBatchByIds(shop_nos);
    }


    /**
     * 分页查询店铺数据
     * 根据 商家名称 类别 地址 模糊查询
     */
    public IPage<Shop> findByPage(@RequestParam Integer pageNum,
                                  @RequestParam Integer pageSize,
                                  @RequestParam(defaultValue = "") String name,
                                  @RequestParam(defaultValue = "") String category,
                                  @RequestParam(defaultValue = "") String location) {
        IPage<Shop> page = new Page<>(pageNum, pageSize);
        QueryWrapper<Shop> queryWrapper = new QueryWrapper<>();
        if (!"".equals(name)) {
            queryWrapper.like("name", name);
        }
        if (!"".equals(category)) {
            queryWrapper.like("category", category);
        }
        if (!"".equals(location)) {
            queryWrapper.like("location", location);
        }
        queryWrapper.orderByDesc("createTime");
        return page(page, queryWrapper);
    }

}
