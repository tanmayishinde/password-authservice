package com.microservice.passwordmanagerservice.vo;

import lombok.Data;

@Data
public class UserVO {
    private String fullName;
    private String email;
    private String phoneNumber;
    private String password;
}
