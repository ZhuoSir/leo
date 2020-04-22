package com.chen.leo.proto;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Header {

    private Map<String, String> headers;

    public Header() {
        this.headers = new ConcurrentHashMap<>();
    }

    public void setHeaders(Map<String, String> headers) {
        this.headers = headers;
    }

    public Map<String, String> getHeaders() {
        return headers;
    }

    @Override
    public String toString() {
        return "Header{" +
                "headers=" + headers +
                '}';
    }
}
