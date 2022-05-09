package com.yizhiteamo.ufood.common;

/**
 * @author Ralts
 * @description ResultCode类
 */
public class RetrieveResultCode {
    /**
     * Http状态码, 200请求成功，400客户端请求语法错误，
     * 401请求要求用户的身份认证
     * 404服务器无法根据客户端的请求找到资源（网页），
     * 500服务器内部错误，无法完成请求
     */
    static int SUCCESS = 200;
    static int FAIL = 400;
    public static int UNAUTHORIZED = 401;
    public static int NOTFOUND =404;
    public static int INTERNAL_SERVER_ERROR = 500;

    public int code;

    RetrieveResultCode(int code) {
        this.code = code;
    }

}

