package com.yang;

import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeoutException;

public class EmitLogDirect {

    private static final String EXCHANGE_NAME = "direct_logs";

    public static void main(String[] args) throws IOException, TimeoutException {

        Channel channel = ConnectionUtil.getConection().createChannel();

        channel.exchangeDeclare(EXCHANGE_NAME, BuiltinExchangeType.DIRECT);

        Map<String, String> bindingKeyMap = new HashMap<>();

        bindingKeyMap.put("info", "普通info信息");
        bindingKeyMap.put("warning", "警告warning信息");
        bindingKeyMap.put("error", "错误error信息");

        bindingKeyMap.put("debug", "调试debug信息");

        for (Map.Entry<String, String> bindingEntry : bindingKeyMap.entrySet()) {
            String bindingKey = bindingEntry.getKey();
            String bindingValue = bindingEntry.getValue();
            channel.basicPublish(EXCHANGE_NAME, bindingKey, null, bindingValue.getBytes("UTF-8"));
            System.out.println("生产者发出消息：" + bindingValue);
        }

    }

}
