package com.yizhiteamo.ufood.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.yizhiteamo.ufood.entity.Shop;
import com.yizhiteamo.ufood.service.ShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Ralts
 */
@RestController
@RequestMapping("/shop")
public class ShopController {

    @Autowired
    private ShopService shopService;


    /**
     *
     * 分页查询用户数据
     * 根据用户名模糊查询
     * offset 为页面偏移量
     */
    @GetMapping("/findByPage")
    public IPage<Shop> findByPage(@RequestParam Integer pageNum,
                                  @RequestParam Integer pageSize,
                                  @RequestParam(defaultValue = "") String name,
                                  @RequestParam(defaultValue = "") String category,
                                  @RequestParam(defaultValue = "") String location) {
        return shopService.findByPage(pageNum, pageSize, name, category, location);
    }

    /**
     *  新增 或 修改 店铺
     */
    @PostMapping("/saveOrUpdateShop")
    public boolean saveOrUpdateShop(@RequestBody Shop shop){
        return shopService.saveOrUpdateShop(shop);
    }

    /**
     *  删除 店铺
     */
    @DeleteMapping("/delShopById/{shopNo}")
    public boolean delShopById(@PathVariable("shopNo") Integer shopNo) {
        return shopService.delShopByID(shopNo);
    }

    /**
     *  批量 删除 店铺
     */
    @PostMapping("/delShopByBatch")
    public boolean delShopByBatch(@RequestBody List<Integer> shop_nos){
        return shopService.delShopByBatch(shop_nos);
    }
}
