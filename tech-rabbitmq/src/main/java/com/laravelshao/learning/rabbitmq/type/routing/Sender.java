package com.laravelshao.learning.rabbitmq.type.routing;

import com.laravelshao.learning.rabbitmq.util.MQConnectUtil;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

/**
 * 模拟后台系统相关更新操作通知前台系统和搜索系统
 * 后台系统就是消息的生产者，前台系统和搜索系统是消息的消费者。
 * 后台系统将消息发送到交换机中，前台系统和搜索系统都创建自己的队列，然后将队列绑定到交换机
 * <p>
 * 前台系统接收消息：修改商品、删除商品
 * 搜索系统接收消息：新增商品、修改商品、删除商品
 *
 * @author shaoqinghua
 * @date 2019/1/4
 * @description 路由模式发送者
 */
public class Sender {

    private final static String EXCHANGE_NAME = "exchange_direct";

    public static void main(String[] argv) throws Exception {

        // 获取连接
        Connection connection = MQConnectUtil.getRabbitMQConnection();
        // 创建通道
        Channel channel = connection.createChannel();

        // 声明direct交换机
        channel.exchangeDeclare(EXCHANGE_NAME, "direct");

        // 消息内容
        String message = "id=1003";
        channel.basicPublish(EXCHANGE_NAME, "delete", null, message.getBytes());
        System.out.println(" [x] Sent '" + message + "'");
    }
}