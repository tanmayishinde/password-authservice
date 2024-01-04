package com.microservice.passwordmanagerservice.helper;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpCookie;
import org.springframework.http.server.reactive.ServerHttpRequest;

import jakarta.servlet.http.Cookie;

import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class SessionHelper {

    public ServerHttpRequest createSession(ServerHttpRequest serverHttpRequest) {
        UUID uuid = UUID.randomUUID();
        String sessionID = uuid.toString();

        serverHttpRequest.mutate().headers((httpHeaders) -> {
            String session = new HttpCookie("session-id", sessionID).toString();
            httpHeaders.set("Cookie", session);
        }).build();
        return serverHttpRequest;
    }


}
