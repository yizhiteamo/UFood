package com.yizhiteamo.ufood.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.yizhiteamo.ufood.entity.Goods;
import com.yizhiteamo.ufood.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Ralts
 */
@RestController
@RequestMapping("/goods")
public class GoodsController {

    @Autowired
    private GoodsService goodsService;


    /**
     *
     * 分页查询用户数据
     * 根据用户名模糊查询
     * offset 为页面偏移量
     */
    @GetMapping("/findByPage")
    public IPage<Goods> findByPage(@RequestParam Integer pageNum,
                                   @RequestParam Integer pageSize,
                                   @RequestParam(defaultValue = "") String name) {
        return goodsService.findByPage(pageNum, pageSize, name);
    }

    /**
     *  新增 或 修改 商品
     */
    @PostMapping("/saveOrUpdateGoods")
    public boolean saveOrUpdateGoods(@RequestBody Goods goods){
        return goodsService.saveOrUpdateGoods(goods);
    }

    /**
     *  删除 商品
     */
    @DeleteMapping("/delGoodsById/{goodsNo}")
    public boolean delGoodsById(@PathVariable("goodsNo") Integer goodsNo) {
        return goodsService.delGoodsByID(goodsNo);
    }

    /**
     *  批量 删除 商品
     */
    @PostMapping("/delGoodsByBatch")
    public boolean delGoodsByBatch(@RequestBody List<Integer> goodsNos){
        return goodsService.delGoodsByBatch(goodsNos);
    }
}
