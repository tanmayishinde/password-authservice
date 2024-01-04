package com.microservice.passwordmanagerservice.helper;

import com.microservice.passwordmanagerservice.constant.Constants;
import com.microservice.passwordmanagerservice.vo.UserVO;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class CookieHelper {
  //  private final JWTHelper jwtHelper;
    private HttpServletRequest httpServletRequest;

    private HttpServletResponse httpServletResponse;


//    public CookieHelper(
////            JWTHelper jwtHelper,
//                        HttpServletRequest httpServletRequest,
//                        HttpServletResponse httpServletResponse) {
////        this.jwtHelper = jwtHelper;
//        this.httpServletRequest = httpServletRequest;
//        this.httpServletResponse = httpServletResponse;
//    }

    public void setCookie(UserVO userVO) throws  Exception {

        int cookieTimeout = Constants.COOKIE_TIMEOUT;
        Cookie sessionCookie = null;
        Cookie[] cookies = httpServletRequest.getCookies();
        for(Cookie cookie : cookies){
            if("session-id".equals(cookie.getName())){
                sessionCookie = cookie;
            }
        }

        if(sessionCookie == null){
            throw new Exception("INVALID_REQUEST");
        }

        httpServletResponse.addCookie(getSessionCookie(sessionCookie, cookieTimeout));
      //  httpServletResponse.addCookie(getJwtCookie(userVO, cookieTimeout));
    }

    public void setCookie() throws  Exception {

        int cookieTimeout = Constants.COOKIE_TIMEOUT;
        Cookie sessionCookie = null;
        Cookie[] cookies = httpServletRequest.getCookies();
        for(Cookie cookie : cookies){
            if("session-id".equals(cookie.getName())){
                sessionCookie = cookie;
            }
        }

        if(sessionCookie == null){
            throw new Exception("INVALID_REQUEST");
        }

        httpServletResponse.addCookie(getSessionCookie(sessionCookie, cookieTimeout));
    }

    private Cookie getSessionCookie(Cookie sessionCookie, int cookieTimeout){
        sessionCookie.setMaxAge(cookieTimeout);
        //sessionCookie.setSecure(true);
        sessionCookie.setHttpOnly(true);
        sessionCookie.setDomain("localhost");
        sessionCookie.setPath("/");
        return sessionCookie;
    }
//    private Cookie getJwtCookie(UserVO userVO, int cookieTimeout){
//        Cookie jwtTokenCookie = new Cookie("auth", jwtHelper.getJwtToken(userVO));
//        jwtTokenCookie.setMaxAge(cookieTimeout);
//        //jwtTokenCookie.setSecure(true);
//        jwtTokenCookie.setHttpOnly(true);
//        jwtTokenCookie.setDomain("localhost");
//        jwtTokenCookie.setPath("/");
//        return jwtTokenCookie;
//    }
}
