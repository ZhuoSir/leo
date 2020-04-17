package com.chen.leo.proxy;

import com.chen.leo.proxy.data.TransportEventData;

public interface TransportEventProxy {

    void connectEvent(TransportEventData eventData);

    void loginEvent(TransportEventData eventData);

    void messageReceivedEvent(TransportEventData eventData);
}
