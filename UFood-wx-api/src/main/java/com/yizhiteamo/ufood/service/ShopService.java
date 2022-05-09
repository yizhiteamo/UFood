package com.yizhiteamo.ufood.service;

import com.yizhiteamo.ufood.entity.Shop;
import com.yizhiteamo.ufood.entity.ShopCate;
import com.yizhiteamo.ufood.mapper.ShopMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
public class ShopService {

    @Resource
    private ShopMapper shopMapper;

    /**
     * 查询所有店铺信息
     */
    public List<Shop> findAll(){
        return shopMapper.findAll();
    }

    /**
     * 获取店铺的所有信息
     */
    public Map<String,Object> getShopAndCateList(){
        Map<String,Object> shopMap = new HashMap<>();
        List<ShopCate> shopList = shopMapper.getShopAndCateList();
        shopMap.put("shopList", shopList);
        return shopMap;
    }

    /**
     * 查询热门店铺信息
     */
    public List<Shop> getPopularShopList(){
        return shopMapper.getPopularShopList();
    }

    /**
     * 根据shop_no查询店铺信息
     */
    public List<Shop> getShopByNO(Integer shop_no) {
        return shopMapper.getShopByNO(shop_no);
    }

}
