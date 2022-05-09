package com.yizhiteamo.ufood.utils;

import lombok.Data;

import java.util.List;

/**
 * @author Ralts
 */
@Data
public class PageUtil<T> {
    private Integer pageNum;
    private Integer pageSize;
    private Integer total;
    private List<T> pageData;
}
