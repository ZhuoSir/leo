package com.chen.leo.proto;

public abstract class LeoRequest implements Request {

    private Header header;

    public Header getHeader() {
        return header;
    }

    public void setHeader(Header header) {
        this.header = header;
    }
}
