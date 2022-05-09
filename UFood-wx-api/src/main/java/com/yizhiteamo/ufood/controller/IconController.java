package com.yizhiteamo.ufood.controller;

import com.yizhiteamo.ufood.entity.Icon;
import com.yizhiteamo.ufood.service.IconService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/icon")
public class IconController {

    @Resource
    private IconService iconService;

    @GetMapping("/getCateIcon")
    public List<Icon> getCateIcon(){
        return iconService.getCateIcon();
    }

}
