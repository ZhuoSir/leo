package com.chen.leo.session;

import com.chen.leo.common.Constants;
import io.netty.channel.ChannelHandlerContext;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class GenericSessionManager implements SessionManager {

    private Map<String, Session> sessionPool = new ConcurrentHashMap<>();

    @Override
    public Session createSession(ChannelHandlerContext ctx) {
        String sessionId = UUID.randomUUID().toString().replace("-", "");
        Session session = new Session(sessionId, ctx);
        sessionPool.put(sessionId, session);
        session.attrs(Constants.SessionConfig.SESSION_KEY, sessionId);
        return session;
    }

    @Override
    public Session getSession(String sessionId) {
        return sessionPool.get(sessionId);
    }

    @Override
    public Session removeSession(String sessionId) {
        return sessionPool.remove(sessionId);
    }

    @Override
    public void closeSession(String sessionId) {
        Session session = sessionPool.get(sessionId);
        closeSession(session);
    }

    @Override
    public void closeSession(Session session) {
        if (null != session && session.isConnected()) {
            session.close();
            removeSession(session.getId());
        }
    }
}
