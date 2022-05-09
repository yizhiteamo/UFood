package com.yizhiteamo.ufood.service;

import com.yizhiteamo.ufood.entity.Goods;
import com.yizhiteamo.ufood.entity.Swiper;
import com.yizhiteamo.ufood.mapper.GoodsMapper;
import com.yizhiteamo.ufood.utils.Page;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import java.util.List;

@Service
public class GoodsService {

    @Resource
    private GoodsMapper goodsMapper;

    /**
     *
     * 获取轮播图数据
     *
     */
    public List<Swiper> findIndexSwiper(){
        return goodsMapper.findIndexSwiper();
    }

    /**
     *
     * 根据shop_no查询指定店铺中的商品
     * offset 为页面偏移量
     */
    public Page<Goods> findByPage(@RequestParam(defaultValue = "1") Integer pageNum,
                                  @RequestParam(defaultValue = "10") Integer pageSize,
                                  @RequestParam Integer shop_no ){
        Integer offset = (pageNum - 1) * pageSize;
        List<Goods> goodsData = goodsMapper.findByPage(offset, pageSize,shop_no);
        Page<Goods> page = new Page<>();
        page.setPageData(goodsData);

        Integer total = goodsMapper.countGoods();
        page.setTotal(total);
        page.setPageNum(pageNum);
        page.setPageSize(pageSize);
        return page;
    }

    /**
     *
     * 根据goods_no查询商品
     *
     */
    public List<Goods> findGoodsByGoodsNO(@RequestParam Integer goods_no){
        return goodsMapper.findGoodsByGoodsNO(goods_no);
    }

    /**
     *
     * 根据goods_no对商品的collected字段取反
     *
     */
    public void collectGoods(@RequestParam Goods goods){
        goodsMapper.collectGoods(goods);
    }

    /**
     * 根据商品名称 模糊查询 商品数据
     */
    public List<Goods> findByLike(String name) {
        return goodsMapper.findByLike(name);
    }

}
