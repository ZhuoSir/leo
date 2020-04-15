package com.chen.leo.session;

import io.netty.channel.ChannelHandlerContext;

public interface SessionManager {

    Session createSession(ChannelHandlerContext ctx);

    Session getSession(String sessionId);

    Session getSessionByAccountId(String accountId);

    void updateSession(Session imSession);

    void removeSession(String sessionId);

    void closeSession(String sessionId);

    void closeSession(Session imSession);

}
