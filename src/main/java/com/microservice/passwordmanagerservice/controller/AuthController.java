package com.microservice.passwordmanagerservice.controller;

import com.microservice.passwordmanagerservice.service.AuthService;
import com.microservice.passwordmanagerservice.utils.SessionUtil;
import com.microservice.passwordmanagerservice.vo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

@RestController
@RequestMapping("/user")
public class AuthController {
    private final AuthService authService;
    @Autowired
    private SessionUtil sessionUtil;


    @Autowired
    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    public RegisterResponseVO registerUser(@RequestBody UserVO userVO) throws Exception {
        return authService.registerUser(userVO);
    }

    @PostMapping("/sendOtp")
    public OtpResponseVO sendOTP(@RequestBody OtpRequestVO requestVO) {
        return authService.sendOTP(requestVO);
    }

    @PostMapping("/validateOtp")
    public OtpResponseVO validateOTP(@RequestBody ValidateOtpRequestVO requestVO) {
        return authService.validateOtp(requestVO);
    }


}
