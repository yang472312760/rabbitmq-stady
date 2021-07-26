package com.yang;

import com.rabbitmq.client.Channel;

import java.io.IOException;
import java.util.Scanner;
import java.util.concurrent.TimeoutException;

public class Task03 {

    public static void main(String[] args) throws IOException, TimeoutException {

        Channel channel = ConnectionUtil.getConection().createChannel();

        String queueName = channel.queueDeclare().getQueue();

        Scanner scanner = new Scanner(System.in);

        while (scanner.hasNext()){
            String message = scanner.nextLine();
            channel.basicPublish("", queueName, null, message.getBytes("UTF-8"));
            System.out.println("生产者发出消息：" + message);
        }

    }

}
