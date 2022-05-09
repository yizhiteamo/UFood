package com.yizhiteamo.ufood.controller;

import com.yizhiteamo.ufood.entity.Shop;
import com.yizhiteamo.ufood.service.ShopService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/shop")
public class ShopController {

    @Resource
    private ShopService shopService;

    @GetMapping("/findAll")
    public List<Shop> findAll(){
        return shopService.findAll();
    }


    @GetMapping("/getShopAndCateList")
    public Map<String, Object> getShopAndCateList(){
       return shopService.getShopAndCateList();
    }

    @GetMapping("/getPopularShopList")
    public List<Shop> getPopularShopList(){
        return shopService.getPopularShopList();
    }

    @GetMapping("/getShopByNO")
    public List<Shop> getShopByNO(@RequestParam Integer shop_no) {
        return shopService.getShopByNO(shop_no);
    }

}
