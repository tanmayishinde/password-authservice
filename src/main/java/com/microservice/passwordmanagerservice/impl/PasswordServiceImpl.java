package com.microservice.passwordmanagerservice.impl;

import com.microservice.passwordmanagerservice.entity.PasswordEntity;
import com.microservice.passwordmanagerservice.repo.PasswordRepo;
import com.microservice.passwordmanagerservice.service.PasswordService;
import com.microservice.passwordmanagerservice.utils.PasswordUtil;
import com.microservice.passwordmanagerservice.vo.DeleteRequestVO;
import com.microservice.passwordmanagerservice.vo.PasswordResponseVO;
import com.microservice.passwordmanagerservice.vo.PasswordVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.ArrayList;
import java.util.List;

@Service
public class PasswordServiceImpl implements PasswordService {
    private PasswordRepo passwordRepo;

    @Autowired
    public PasswordServiceImpl(PasswordRepo passwordRepo) {
        this.passwordRepo = passwordRepo;
    }


    @Override
    public PasswordResponseVO addPassword(PasswordVO passwordVO) throws Exception {
        PasswordResponseVO passwordResponseVO = new PasswordResponseVO();
        PasswordEntity password = new PasswordEntity();
       // password.setId(passwordVO.getId());
        password.setUrl(passwordVO.getUrl());
        password.setCategory(passwordVO.getCategory());
        password.setUsername(passwordVO.getUsername());
        password.setPassword(PasswordUtil.encryptPassword(passwordVO.getPassword()));
        passwordRepo.save(password);
        passwordResponseVO.setResponseCode("200");
        passwordResponseVO.setResponseMsg("Password successfully Added!");
        //passwordResponseVO;

        return passwordResponseVO;
    }

    @Override
    public List<PasswordVO> passwordList() throws Exception {
       // List<PasswordEntity> passwordEntityList = passwordRepo.findByusername(pa);
        List<PasswordEntity> passwordEntityList = passwordRepo.findAll();
        List<PasswordVO> passwordVOList = new ArrayList<>();
        for (PasswordEntity password : passwordEntityList) {
            PasswordVO passwordVO = new PasswordVO();
            passwordVO.setUrl(password.getUrl());
            passwordVO.setCategory(password.getCategory());
            passwordVO.setUsername(password.getUsername());
            passwordVO.setPassword(PasswordUtil.decryptPassword(password.getPassword()));
            passwordVOList.add(passwordVO);
        }
        return passwordVOList;
    }

    @Override
    public PasswordResponseVO updatePassword(PasswordVO passwordVO) throws Exception {
        PasswordResponseVO passwordResponseVO = new PasswordResponseVO();
        PasswordEntity password=passwordRepo.findByusername(passwordVO.getUsername());
        if(password == null){
            throw new NullPointerException("No such user exists,retry with a valid username.");
        }
        else {
            password.setUrl(passwordVO.getUrl());
            password.setCategory(passwordVO.getCategory());
            password.setUsername(passwordVO.getUsername());
            password.setPassword(PasswordUtil.encryptPassword(passwordVO.getPassword()));
            passwordRepo.save(password);
            passwordResponseVO.setResponseCode("200");
            passwordResponseVO.setResponseMsg("Password successfully Updated!");
        }
        return passwordResponseVO;
    }
@Override
    public PasswordResponseVO deletePassword(DeleteRequestVO deleteRequestVO){
        PasswordResponseVO passwordResponseVO=new PasswordResponseVO();
        PasswordEntity password=passwordRepo.findByusername(deleteRequestVO.getUsername());
        if(password == null){
            throw new NullPointerException("No such user exists,retry with a valid username.");
        }
        else {
            passwordRepo.deleteByusername(password.getUsername());
            passwordResponseVO.setResponseCode("200");
            passwordResponseVO.setResponseMsg("Password Deleted Successfully!");

        }
        return passwordResponseVO;

    }
}
