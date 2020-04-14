package com.chen.leo.transport;

public interface TransportServer {

    void init(final Integer port);

    void start(boolean sync);
}
