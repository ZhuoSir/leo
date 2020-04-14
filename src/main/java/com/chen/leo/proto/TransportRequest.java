package com.chen.leo.proto;

public abstract class TransportRequest implements Request {

    private Header header;

    public Header getHeader() {
        return header;
    }

    public void setHeader(Header header) {
        this.header = header;
    }
}
