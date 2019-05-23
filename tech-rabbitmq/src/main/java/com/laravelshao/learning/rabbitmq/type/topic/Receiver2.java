package com.laravelshao.learning.rabbitmq.type.topic;


import com.laravelshao.learning.rabbitmq.util.MQConnectUtil;
import com.rabbitmq.client.*;

import java.io.IOException;

/**
 * 模拟搜索系统消费者
 * 接收消息：新增商品、修改商品、删除商品
 *
 * @author shaoqinghua
 * @date 2019/1/4
 * @description 通配符(主题)模式消费者
 */
public class Receiver2 {

    private final static String QUEUE_NAME = "queue_topic_2";

    private final static String EXCHANGE_NAME = "exchange_topic";

    public static void main(String[] argv) throws Exception {

        // 获取连接
        Connection connection = MQConnectUtil.getRabbitMQConnection();
        // 获取通道
        Channel channel = connection.createChannel();

        // 声明队列
        channel.queueDeclare(QUEUE_NAME, false, false, false, null);
        // 绑定队列到交换机
        channel.queueBind(QUEUE_NAME, EXCHANGE_NAME, "item.#"); // # 匹配一个或多个词 * 匹配不多不少一个词
        // 同一时刻服务器只会发一条消息给消费者
        channel.basicQos(1);

        // 定义队列的消费者
        DefaultConsumer consumer = new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                String message = new String(body);
                System.out.println(" 搜索系统: '" + message + "'");
                // 设置确认状态
                channel.basicAck(envelope.getDeliveryTag(), false);
            }
        };
        // 监听队列手动确认
        channel.basicConsume(QUEUE_NAME, false, consumer);

    }
}