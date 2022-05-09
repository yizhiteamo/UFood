package com.yizhiteamo.ufood.exception;

import lombok.Getter;

/**
 * 自定义异常
 * @author Ralts
 */
@Getter
public class ServiceException extends RuntimeException {
    private String code;

    public ServiceException(String code, String msg) {
        super(msg);
        this.code = code;
    }

}
