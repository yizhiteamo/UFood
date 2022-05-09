package com.yizhiteamo.ufood.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yizhiteamo.ufood.entity.Swiper;
import com.yizhiteamo.ufood.mapper.SwiperMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @author Ralts
 */
@Service
public class SwiperService extends ServiceImpl<SwiperMapper, Swiper> {

    /**
     * 新增或修改数据
     */
    public boolean saveOrUpdateSwiper(Swiper swiper) {
        return saveOrUpdate(swiper);
    }

    /**
     *  删除 店铺
     */
    public boolean delSwiperByID(Integer id){
        return removeById(id);
    }

    /**
     *  批量 删除 店铺
     */
    public boolean delSwiperByBatch(List<Integer> ids){
        return removeBatchByIds(ids);
    }


    /**
     * 分页查询店铺数据
     * 根据 商家名称 类别 地址 模糊查询
     */
    public IPage<Swiper> findByPage(@RequestParam Integer pageNum,
                                  @RequestParam Integer pageSize) {
        IPage<Swiper> page = new Page<>(pageNum, pageSize);
        QueryWrapper<Swiper> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("id");
        return page(page, queryWrapper);
    }


}
