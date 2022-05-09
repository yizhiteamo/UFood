package com.yizhiteamo.ufood.utils;

import lombok.Data;

import java.util.List;

@Data
public class Page<T> {

    private Integer pageNum;
    private Integer pageSize;
    private Integer total;
    private List<T> pageData;
}
