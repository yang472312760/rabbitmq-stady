package com.yang;

import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class ConnectionUtil {

    public static Connection getConection() throws IOException, TimeoutException {

        //创建连接工厂
        ConnectionFactory factory = new ConnectionFactory();

        factory.setHost("192.168.220.142");

        factory.setPort(5672);

        factory.setVirtualHost("/");

        factory.setUsername("yang");

        factory.setPassword("123456");

        return  factory.newConnection();
    }

}
