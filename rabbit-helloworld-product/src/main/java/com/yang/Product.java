package com.yang;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class Product {

    static final String QUEUE_NAME = "simple_queue";

    public static void main(String[] args) throws IOException, TimeoutException {

        //创建连接工厂
        ConnectionFactory factory = new ConnectionFactory();
        //主机地址：默认为localhost
        factory.setHost("192.168.220.141");
        //连接端口
        factory.setPort(5672);
        //虚拟主机名称：默认 /
        factory.setVirtualHost("/");
        //连接用户名：默认为guest
        factory.setUsername("yang");
        //连接密码：默认guest
        factory.setPassword("123456");
        //创建连接
        Connection connection = factory.newConnection();

        //创建频道
        Channel channel = connection.createChannel();

        /**
         * 创建队列
         *
         * 参数一：队列名称
         * 参数二：是否定义持久化队列
         * 参数三：是否独立本次连接
         * 参数四：是否在不使用的时候自动删除队列
         * 参数五：队列其他参数
         *
         */
        channel.queueDeclare(QUEUE_NAME, true, false, false, null);

        //要发送的消息
        String message = "wo shi yi ge product";

        channel.basicPublish("", QUEUE_NAME, null, message.getBytes());

        System.out.println("已发送消息\t" + message);

        channel.close();
        connection.close();

    }

}
