package com.yizhiteamo.ufood.service;

import com.yizhiteamo.ufood.entity.Icon;
import com.yizhiteamo.ufood.mapper.IconMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class IconService {

    @Resource
    private IconMapper iconMapper;

    public List<Icon> getCateIcon(){
        return iconMapper.getCateIcon();
    }

}
