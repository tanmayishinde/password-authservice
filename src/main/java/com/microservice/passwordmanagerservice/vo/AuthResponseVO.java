package com.microservice.passwordmanagerservice.vo;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
public class AuthResponseVO {

    private boolean validUser;
    private UserVO user;

}
