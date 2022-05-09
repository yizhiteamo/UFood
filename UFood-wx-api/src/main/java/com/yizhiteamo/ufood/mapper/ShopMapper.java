package com.yizhiteamo.ufood.mapper;

import com.yizhiteamo.ufood.entity.Shop;
import com.yizhiteamo.ufood.entity.ShopCate;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ShopMapper {

    List<Shop> findAll();

    List<ShopCate> getShopAndCateList();

    List<Shop> getPopularShopList();

    List<Shop> getShopByNO(Integer shop_no);

}
