package com.chen.leo.proto;

public class Content {

    private CharSequence body;

    public void setBody(CharSequence body) {
        this.body = body;
    }

    public CharSequence getBody() {
        return body;
    }

    @Override
    public String toString() {
        return "Content{" +
                "body='" + body + '\'' +
                '}';
    }
}
