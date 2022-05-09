package com.yizhiteamo.ufood.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yizhiteamo.ufood.entity.Goods;
import com.yizhiteamo.ufood.mapper.GoodsMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @author Ralts
 */
@Service
public class GoodsService extends ServiceImpl<GoodsMapper, Goods> {


    /**
     * 新增或修改数据
     */
    public boolean saveOrUpdateGoods(Goods goods) {
        return saveOrUpdate(goods);
    }

    /**
     *  删除 商品
     */
    public boolean delGoodsByID(Integer goods_no){
        return removeById(goods_no);
    }

    /**
     *  批量 删除 商品
     */
    public boolean delGoodsByBatch(List<Integer> goods_nos){
        return removeBatchByIds(goods_nos);
    }


    /**
     * 分页查询商品数据
     * 根据 商家名称 类别 地址 模糊查询
     */
    public IPage<Goods> findByPage(@RequestParam Integer pageNum,
                                  @RequestParam Integer pageSize,
                                  @RequestParam(defaultValue = "") String name) {
        IPage<Goods> page = new Page<>(pageNum, pageSize);
        QueryWrapper<Goods> queryWrapper = new QueryWrapper<>();
        if (!"".equals(name)) {
            queryWrapper.like("name", name);
        }
        queryWrapper.orderByDesc("goods_no");
        return page(page, queryWrapper);
    }

}
