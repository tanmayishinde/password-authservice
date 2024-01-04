package com.microservice.passwordmanagerservice.service;

import com.microservice.passwordmanagerservice.utils.SendMailUtil;
import com.microservice.passwordmanagerservice.vo.OtpResponseVO;
import org.springframework.stereotype.Component;

import java.util.concurrent.CompletableFuture;

@Component
public class AsyncService {
    public CompletableFuture<OtpResponseVO> sendMail(String mail) {
        OtpResponseVO otpResponseVO = new OtpResponseVO();
        String otp = SendMailUtil.generateOtp();
        SendMailUtil.sendMail(otp, mail);

        otpResponseVO.setResponseCode("200");
        otpResponseVO.setResponseMsg("SEND_OTP_SUCCESS");

        return CompletableFuture.completedFuture(otpResponseVO);
    }

}
