package com.yizhiteamo.ufood.mapper;

import com.yizhiteamo.ufood.entity.Goods;
import com.yizhiteamo.ufood.entity.Swiper;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

public interface GoodsMapper {

    List<Swiper> findIndexSwiper();

    List<Goods> findGoodsByGoodsNO(Integer goods_no);

    List<Goods> findByPage(Integer offset, Integer pageSize,Integer shop_no);
    
    List<Goods> findByLike(String name);

    Integer countGoods();

    @Transactional
    void collectGoods(Goods goods);

}
