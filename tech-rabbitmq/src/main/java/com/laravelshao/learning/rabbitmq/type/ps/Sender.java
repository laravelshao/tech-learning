package com.laravelshao.learning.rabbitmq.type.ps;


import com.laravelshao.learning.rabbitmq.util.MQConnectUtil;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

/**
 * @author shaoqinghua
 * @date 2019/1/4
 * @description 订阅模式(fanout广播模式)发送者
 */
public class Sender {

    private final static String EXCHANGE_NAME = "exchange_fanout";

    public static void main(String[] argv) throws Exception {

        // 获取连接
        Connection connection = MQConnectUtil.getRabbitMQConnection();
        // 创建通道
        Channel channel = connection.createChannel();

        // 声明fanout交换机
        // String exchange, String type, boolean durable, boolean autoDelete, Map<String, Object> arguments
        channel.exchangeDeclare(EXCHANGE_NAME, "fanout");

        // 消息内容
        String message = "fanout-message";
        // String exchange, String routingKey, BasicProperties props, byte[] body
        channel.basicPublish(EXCHANGE_NAME, "", null, message.getBytes());
        System.out.println(" [x] Sent '" + message + "'");
    }
}