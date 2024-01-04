package com.microservice.passwordmanagerservice.vo;

import lombok.Data;

@Data
public class PasswordResponseVO {
    //    private boolean passwordAddedSuccessfully;
    private String responseCode;
    private String responseMsg;

}
