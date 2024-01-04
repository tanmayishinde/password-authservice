package com.microservice.passwordmanagerservice.service;
import com.microservice.passwordmanagerservice.vo.DeleteRequestVO;
import com.microservice.passwordmanagerservice.vo.PasswordResponseVO;
import com.microservice.passwordmanagerservice.vo.PasswordVO;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.List;

public interface PasswordService {
    PasswordResponseVO addPassword(PasswordVO passwordVO) throws Exception;
    List<PasswordVO> passwordList() throws Exception;

    PasswordResponseVO updatePassword(PasswordVO passwordVO) throws Exception;

    PasswordResponseVO deletePassword(DeleteRequestVO deleteRequestVO);
}
