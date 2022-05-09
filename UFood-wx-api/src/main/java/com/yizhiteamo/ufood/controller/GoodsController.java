package com.yizhiteamo.ufood.controller;

import com.yizhiteamo.ufood.entity.Goods;
import com.yizhiteamo.ufood.entity.Swiper;
import com.yizhiteamo.ufood.service.GoodsService;
import com.yizhiteamo.ufood.utils.Page;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author Ralts
 * @RestController 将数据渲染成JSON
 */
@RestController
@RequestMapping("/goods")
public class GoodsController {

    @Resource
    private GoodsService goodsService;

    /**
     * 获取轮播图数据
     */
    @GetMapping("/findIndexSwiper")
    public List<Swiper> findIndexSwiper(){
        return goodsService.findIndexSwiper();
    }

    /**
     * 根据shop_no查询指定店铺中的商品
     * offset 为页面偏移量
     */
    @GetMapping("/findGoodsByPage")
    public Page<Goods> findByPage(@RequestParam(defaultValue = "1") Integer pageNum,
                                  @RequestParam(defaultValue = "10") Integer pageSize,
                                  @RequestParam Integer shop_no){
        return goodsService.findByPage(pageNum, pageSize,shop_no);
    }

    /**
     * 根据goods_no查询商品
     */
    @GetMapping("/findGoodsByGoodsNO")
    public List<Goods> findGoodsByGoodsNO(@RequestParam Integer goods_no){
        return goodsService.findGoodsByGoodsNO(goods_no);
    }

    /**
     * 根据goods_no对商品的collected字段取反
     */
    @PutMapping("/collectGoods")
    public String collectGoods(@RequestBody Goods goods){
        goodsService.collectGoods(goods);
        return "success";
    }

    /**
     * 根据商品名称 模糊查询 商品数据
     */
    @GetMapping("/findByLike")
    public List<Goods> findByLike(@RequestParam(defaultValue = "") String name) {
        return goodsService.findByLike(name);
    }

}
