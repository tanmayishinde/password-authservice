package com.microservice.passwordmanagerservice.impl;

import com.microservice.passwordmanagerservice.entity.UserEntity;
import com.microservice.passwordmanagerservice.helper.CookieHelper;
import com.microservice.passwordmanagerservice.helper.SessionHelper;
import com.microservice.passwordmanagerservice.repo.UserRepo;
import com.microservice.passwordmanagerservice.service.AsyncService;
import com.microservice.passwordmanagerservice.service.AuthService;
import com.microservice.passwordmanagerservice.utils.PasswordUtil;
import com.microservice.passwordmanagerservice.utils.SessionUtil;
import com.microservice.passwordmanagerservice.utils.ValidationUtil;
import com.microservice.passwordmanagerservice.vo.*;
import io.micrometer.common.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@Service
public class AuthServiceImpl implements AuthService {
    private final UserRepo userRepo;
    private final AsyncService asyncService;
    private final SessionUtil sessionUtil;
    private final CookieHelper cookieHelper;

    @Autowired
    public AuthServiceImpl(UserRepo userRepo,
                           AsyncService asyncService,
                           SessionUtil sessionUtil,
                           CookieHelper cookieHelper
                            ) {
        this.userRepo = userRepo;
        this.asyncService = asyncService;
        this.sessionUtil = sessionUtil;
        this.cookieHelper = cookieHelper;
    }

    @Override
    public RegisterResponseVO registerUser(UserVO userVO) throws Exception {
        String sessionId = sessionUtil.getSessionId();

        if (userVO.getEmail().isEmpty() ||
                ValidationUtil.inValidEmailCheck(userVO.getEmail())) {
            throw new IllegalArgumentException("Email is Invalid/is Empty. Please provide a valid Email Address!");

        }
        if (userVO.getPhoneNumber().isEmpty() ||
                ValidationUtil.inValidatePhoneNumberCheck(userVO.getPhoneNumber())) {
            throw new IllegalArgumentException("PhoneNumber is Invalid/is Empty. Please provide a valid PhoneNumber!");

        }

        if (userVO.getPassword().isEmpty() ||
                ValidationUtil.inValidatePassword(userVO.getPassword())) {
            throw new IllegalArgumentException("Password is Invalid/is Empty. Please provide a valid Password!");

        }
        RegisterResponseVO registerResponseVO = new RegisterResponseVO();

        UserEntity userEntity = new UserEntity();
        userEntity.setEmail(userVO.getEmail());
        userEntity.setFullName(userVO.getFullName());
        userEntity.setPhoneNumber(userVO.getPhoneNumber());
        userEntity.setPassword(PasswordUtil.encryptPassword(userVO.getPassword()));
        userRepo.save(userEntity);
        registerResponseVO.setRegisterUserSuccessful(true);
        return registerResponseVO;
    }

    @Override
    public OtpResponseVO sendOTP(OtpRequestVO requestVO) {

        if (ValidationUtil.inValidEmailCheck(requestVO.getEmail())) {
            throw new IllegalArgumentException("Email is Invalid/is Empty. Please provide a valid Email Address!");

        }
        OtpResponseVO otpResponseVO;
        String sessionId = sessionUtil.getSessionId();
        UserEntity userEntity = userRepo.findByEmail(requestVO.getEmail());
        if (userEntity != null && StringUtils.isNotEmpty(userEntity.getEmail())) {

            try {
               // cookieHelper.setCookie(userEntity);
              //  String otp = SendMailUtil.generateOtp();
                CompletableFuture<OtpResponseVO> otpResponseVOasync =
                        asyncService.sendMail(requestVO.getEmail());
                otpResponseVO = otpResponseVOasync.get();


            } catch (InterruptedException | ExecutionException e) {
                throw new RuntimeException(e);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            return otpResponseVO;
        }
        throw new IllegalArgumentException("Email is Invalid/is Empty. Please provide a valid Email Address!");
    }

    @Override
    public OtpResponseVO validateOtp(ValidateOtpRequestVO requestVO) {


        return null;
    }


}
