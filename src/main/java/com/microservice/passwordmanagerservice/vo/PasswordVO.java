package com.microservice.passwordmanagerservice.vo;

import lombok.Data;

@Data
public class PasswordVO {
    private String url;
    private String category;
    private String username;
    private String password;
}
