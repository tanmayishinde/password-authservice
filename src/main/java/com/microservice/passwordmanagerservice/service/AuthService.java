package com.microservice.passwordmanagerservice.service;

import com.microservice.passwordmanagerservice.vo.*;
import org.springframework.web.bind.annotation.RequestBody;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

public interface AuthService {
    RegisterResponseVO registerUser(@RequestBody UserVO userVO) throws Exception;
    OtpResponseVO sendOTP(OtpRequestVO requestVO);

    OtpResponseVO validateOtp(ValidateOtpRequestVO requestVO);
}
