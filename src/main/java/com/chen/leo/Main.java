package com.chen.leo;

import com.chen.leo.transport.TransportServer;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {

    public static void main(String[] args) {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
        TransportServer transportServer = (TransportServer) ctx.getBean("transportServer");
        transportServer.init(7000);
        transportServer.start(true);
    }

}
