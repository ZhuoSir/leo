package com.chen.leo.proxy;

import com.chen.leo.proto.TransportRequest;
import com.chen.leo.proxy.data.TransportEventData;
import com.chen.leo.session.Session;

public interface TransportEventProxy {

    void connect(Session session);

    void transportRequest(Session session, TransportRequest transportRequest);
}
