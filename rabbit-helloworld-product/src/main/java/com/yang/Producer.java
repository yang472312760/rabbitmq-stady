package com.yang;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class Producer {

    private static final String QUEUE_NAME = "hello";

    public static void main(String[] args) throws IOException, TimeoutException {

        Channel channel = ConnectionUtil.getConection().createChannel();

        AMQP.BasicProperties properties = new AMQP.BasicProperties().builder().priority(5).build();

        for (int i = 0; i < 11; i++) {
            String message = "info" + i;
            if (i == 5) {
                channel.basicPublish("", QUEUE_NAME, properties, message.getBytes());
            } else {
                channel.basicPublish("", QUEUE_NAME, null, message.getBytes());
            }
            System.out.println("发送消息完成：" + message);
        }

    }

}
