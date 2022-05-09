package com.yizhiteamo.ufood.utils;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;


/**
 * @author Ralts
 * @description 邮箱配置
 */
@Data
@Component
public class EmailConfig {
    /**
     * 发件人邮箱
     */
    @Value("${spring.mail.username}")
    private String emailForm;
}
