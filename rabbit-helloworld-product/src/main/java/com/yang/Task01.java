package com.yang;

import com.rabbitmq.client.Channel;

import java.io.IOException;
import java.util.Scanner;
import java.util.concurrent.TimeoutException;

public class Task01 {

    private static final String QUEUE_NAME = "hello";

    public static void main(String[] args) throws IOException, TimeoutException {

        Channel channel = ConnectionUtil.getConection().createChannel();

        Scanner scanner = new Scanner(System.in);

        while (scanner.hasNext()) {
            String next = scanner.next();
            channel.basicPublish("", QUEUE_NAME, null, next.getBytes());
            System.out.println("发送消息完成：" + next);
        }

    }

}
