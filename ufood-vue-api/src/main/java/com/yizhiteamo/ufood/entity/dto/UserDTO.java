package com.yizhiteamo.ufood.entity.dto;

import lombok.Data;

/**
 * 接收 前端登录请求的参数
 * @author Ralts
 */
@Data
public class UserDTO {
    private String username;
    private String password;
    private String email;
    private String nickname;
    private String avatar_url;


}
