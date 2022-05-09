package com.yizhiteamo.ufood.common;


import org.springframework.web.bind.annotation.CrossOrigin;

/**
 * @author Ralts
 * @description
 */
@CrossOrigin(value = "http://localhost:8080", maxAge = 1800, allowedHeaders ="Content-Type")
public class RetrieveResultFactory {

    public static RetrieveResult buildResult(int resultCode, String message, Object data) {
        return new RetrieveResult(resultCode, message, data);
    }

    /**
     * 连接错误
     * @param message 错误信息
     * @return Result
     */
    public static RetrieveResult buildFailResult(String message) {
        return buildResult(RetrieveResultCode.FAIL, message, null);
    }

    /**
     * 连接成功
     * @param data 返回数据
     * @return Result
     */
    public static RetrieveResult buildSuccessResult(Object data) {
        return buildResult(RetrieveResultCode.SUCCESS, "成功", data);
    }

    /**
     * 操作成功
     * @param message 提示信息
     * @param data 传回数据
     * @return Result对象
     */
    public static RetrieveResult buildSuccessResult(String message, Object data) {
        return buildResult(RetrieveResultCode.SUCCESS, message, data);
    }
}

