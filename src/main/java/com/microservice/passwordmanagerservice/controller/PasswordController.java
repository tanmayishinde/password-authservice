package com.microservice.passwordmanagerservice.controller;

import com.microservice.passwordmanagerservice.service.PasswordService;
import com.microservice.passwordmanagerservice.utils.ValidationUtil;
import com.microservice.passwordmanagerservice.vo.DeleteRequestVO;
import com.microservice.passwordmanagerservice.vo.PasswordResponseVO;
import com.microservice.passwordmanagerservice.vo.PasswordVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.List;

@RestController
@RequestMapping("/manager")
public class PasswordController {
    private PasswordService passwordService;
    @Autowired
    public PasswordController(PasswordService passwordService){
        this.passwordService = passwordService;
    }
    @GetMapping("/getPasswordList")
    public List<PasswordVO> passwordList() throws Exception {
       return passwordService.passwordList();
    }

    @PostMapping("/addPassword")
    public ResponseEntity<?> addPasswordDetails(@RequestBody PasswordVO passwordVO) throws Exception {
        if (passwordVO.getPassword().isEmpty() || ValidationUtil.inValidatePassword(passwordVO.getPassword())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Password is Invalid/is Empty. Please provide a valid Password.");
        }
        return ResponseEntity.ok(this.passwordService.addPassword(passwordVO));
    }

    @PutMapping("/updatePassword")
    public ResponseEntity<?> updatePasswordDetails(@RequestBody PasswordVO passwordVO) throws Exception {
        if (passwordVO.getPassword().isEmpty() || ValidationUtil.inValidatePassword(passwordVO.getPassword())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Password is Invalid/is Empty. Please provide a valid Password.");
        }
        return ResponseEntity.ok(this.passwordService.updatePassword(passwordVO));
    }

    @DeleteMapping("/deletePassword")
    public PasswordResponseVO deleteByUsername(@RequestBody DeleteRequestVO deleteRequestVO){
        return  passwordService.deletePassword(deleteRequestVO);

    }

}
