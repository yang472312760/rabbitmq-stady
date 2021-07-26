package com.yang;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.DeliverCallback;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class Worker03Task02 {

    private static final String ACK_QUEUE = "ack_queue";

    public static void main(String[] args) throws IOException, TimeoutException {

        Channel channel = ConnectionUtil.getConection().createChannel();

        System.out.println("C1等待接收消息处理时间较短");

        DeliverCallback deliverCallback = (consumerTag, delivery) -> {
            String message = new String(delivery.getBody());
            SleepUtils.sleep(1);
            System.out.println("接收到消息：" + message);
            channel.basicAck(delivery.getEnvelope().getDeliveryTag(), false);
        };

        boolean autoAck = false;

        channel.basicConsume(ACK_QUEUE, autoAck, deliverCallback, consumerTag -> {
            System.out.println(consumerTag + "消费者取消消费j接口回调逻辑");
        });

    }

}
