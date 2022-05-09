package com.yizhiteamo.ufood.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.yizhiteamo.ufood.entity.Shop;
import com.yizhiteamo.ufood.entity.Swiper;
import com.yizhiteamo.ufood.service.ShopService;
import com.yizhiteamo.ufood.service.SwiperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Ralts
 */
@RestController
@RequestMapping("/swiper")
public class SwiperController {

    @Autowired
    private SwiperService swiperService;


    /**
     *
     * 分页查询用户数据
     * 根据用户名模糊查询
     * offset 为页面偏移量
     */
    @GetMapping("/findByPage")
    public IPage<Swiper> findByPage(@RequestParam Integer pageNum,
                                  @RequestParam Integer pageSize) {
        return swiperService.findByPage(pageNum, pageSize);
    }

    /**
     *  新增 或 修改 店铺
     */
    @PostMapping("/saveOrUpdateSwiper")
    public boolean saveOrUpdateSwiper(@RequestBody Swiper swiper){
        return swiperService.saveOrUpdateSwiper(swiper);
    }

    /**
     *  删除 店铺
     */
    @DeleteMapping("/delSwiperById/{id}")
    public boolean delSwiperById(@PathVariable("id") Integer id) {
        return swiperService.delSwiperByID(id);
    }

    /**
     *  批量 删除 店铺
     */
    @PostMapping("/delSwiperByBatch")
    public boolean delSwiperByBatch(@RequestBody List<Integer> ids){
        return swiperService.delSwiperByBatch(ids);
    }

}
