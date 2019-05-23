package com.laravelshao.learning.rabbitmq.advanced.dlx;

import com.laravelshao.learning.rabbitmq.util.MQConnectUtil;
import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


/**
 * 消息消费者
 * <p>
 * 消费代码被注释，10秒钟后，消息会从正常队列 PRE_DLX_QUEUE 到达死信交换机
 * DLX_EXCHANGE，然后路由到死信队列DLX_QUEUE
 *
 * @author shaoqinghua
 * @date 2019/1/4
 * @description
 */
public class DlxConsumer {

    public static void main(String[] args) throws Exception {

        // 获取连接
        Connection connection = MQConnectUtil.getRabbitMQConnection();
        // 创建消息通道
        Channel channel = connection.createChannel();

        // 指定队列死信交换机
        Map<String, Object> props = new HashMap<>(16);
        props.put("x-dead-letter-exchange", "DLX_EXCHANGE");
        props.put("x-expires", "9000"); // 设置队列TTL
        props.put("x-max-length", 4); // 如果设置了队列最大长度，超过后会最先入队消息会被发送到DLX

        // 声明队列（默认交换机AMQP default，Direct）
        // String queue, boolean durable, boolean exclusive, boolean autoDelete, Map<String, Object> arguments
        channel.queueDeclare("PRE_DLX_QUEUE", false, false, false, props);

        // 声明死信交换机
        channel.exchangeDeclare("DLX_EXCHANGE", "topic", false, false, false, null);
        // 声明死信队列
        channel.queueDeclare("DLX_QUEUE", false, false, false, null);
        // 绑定，此处 Dead letter routing key 设置为 #
        channel.queueBind("DLX_QUEUE", "DLX_EXCHANGE", "#");
        System.out.println(" Waiting for message....");

        // 创建消费者
        Consumer consumer = new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties,
                                       byte[] body) throws IOException {
                String msg = new String(body, "UTF-8");
                System.out.println("Received message : '" + msg + "'");
            }
        };

        // 开始获取消息
        // String queue, boolean autoAck, Consumer callback
        // channel.basicConsume("PRE_DLX_QUEUE", true, consumer);
    }
}