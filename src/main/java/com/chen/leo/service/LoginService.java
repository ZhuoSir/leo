package com.chen.leo.service;

import com.chen.leo.session.Session;
import com.chen.leo.session.SessionManager;
import io.netty.channel.ChannelHandlerContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginService {

    @Autowired
    private SessionManager sessionManager;

    public Session sessionConnect(ChannelHandlerContext context) {
        return sessionManager.createSession(context);
    }

}
