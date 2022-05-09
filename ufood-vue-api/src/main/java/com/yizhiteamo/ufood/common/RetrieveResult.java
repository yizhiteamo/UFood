package com.yizhiteamo.ufood.common;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * @author Ralts
 * @description 存储响应结果
 */
@Data
@Component
@NoArgsConstructor
public class RetrieveResult {
    /**
     * 响应码，结果信息，数据
     */
    private int code;
    private String message;
    private Object result;

    RetrieveResult(int code, String message, Object data) {
        this.code = code;
        this.message = message;
        this.result = data;
    }

}

