package com.laravelshao.learning.rabbitmq.type.work;


import com.laravelshao.learning.rabbitmq.util.MQConnectUtil;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

/**
 * @author shaoqinghua
 * @date 2019/1/4
 * @description Work队列发送者
 */
public class Sender {

    private final static String QUEUE_NAME = "work_queue";

    public static void main(String[] argv) throws Exception {

        // 获取连接
        Connection connection = MQConnectUtil.getRabbitMQConnection();
        // 创建通道
        Channel channel = connection.createChannel();

        // 声明队列
        channel.queueDeclare(QUEUE_NAME, false, false, false, null);

        for (int i = 0; i < 50; i++) {
            String message = "message-" + i;
            channel.basicPublish("", QUEUE_NAME, null, message.getBytes());
            System.out.println(" [x] Sent '" + message + "'");

            Thread.sleep(i * 10);
        }

    }
}