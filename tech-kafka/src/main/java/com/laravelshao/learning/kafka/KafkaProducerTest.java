package com.laravelshao.learning.kafka;

import org.apache.kafka.clients.producer.*;

import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * 生产者配置选项参考 {@link ProducerConfig}
 *
 * @author shaoqinghua
 * @date 2018/11/5
 * @description kafka生产者测试
 */
public class KafkaProducerTest {

    public static void main(String[] args) throws ExecutionException, InterruptedException, IOException {
        /**
         * acks：表示producer发送消息至broker后的确认值
         *      0：表示producer不需要等待broker消息确认，该选项时延最小但风险最大（因为当server宕机时数据将会丢失）
         *      1(默认值)：表示producer只需要获得kafka集群中leader节点确认即可，该选择时延较小同时确保leader节点确认接收成功
         *      -1(all)：需要ISR中所有Replica给予接收确认，速度最慢，安全性最高，但是由于ISR可能会缩小到仅包含一个Replica，所以设置参数为all并不能一定避免数据丢失
         *
         * batch.size
         *      生产者发送多个消息到broker上的同一个分区时，为了减少网络请求带来的性能开销，
         *      通过批量的方式来提交消息，可以通过这个参数来控制批量提交的字节数大小，
         *      默认大小是16384byte,也就是16kb，意味着当一批消息大小达到指定的batch.size的时候会统一发送
         *
         * linger.ms
         *      Producer默认会把两次发送时间间隔内收集到的所有Requests进行一次聚合然后再发送，以此提高吞吐量，
         *      而linger.ms就是为每次发送到broker的请求增加一些delay，以此来聚合更多的Message请求。
         *      类似于TCP里面的Nagle算法，在TCP协议的传输中，为了减少大量小数据包的发送，采用了Nagle算法，也就是基于小包的等-停协议
         *
         * max.request.size
         *      设置请求数据最大字节数，为了防止发生较大的数据包影响到吞吐量，默认值为1MB
         */
        Properties props = new Properties();
        props.put("bootstrap.servers", "172.16.105.128:9092");
        props.put("client.id", "KafkaProducerTest");
        props.put("acks", "-1");
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");

        Producer<String, String> producer = new KafkaProducer<>(props);

        for (int i = 0; i < 10; i++) {
            System.out.println("send message: sync-message-" + i);
            // 同步发送消息
            Future<RecordMetadata> future = producer.send(new ProducerRecord<String, String>("test", "sync-message-" + i));
            RecordMetadata recordMetadata = future.get(); // 阻塞
            System.out.println("sync send->offset: " +
                    recordMetadata.offset() + " partition: " + recordMetadata.partition());
        }

        //for (int i = 0; i < 10; i++) {
        //    System.out.println("send message: async-message-" + i);
        //    // 异步发送消息
        //    producer.send(new ProducerRecord<String, String>("test", "async-message-" + i), new Callback() {
        //        @Override
        //        public void onCompletion(RecordMetadata recordMetadata, Exception e) {
        //            System.out.println("async send->offset: " +
        //                    recordMetadata.offset() + " partition: " + recordMetadata.partition());
        //        }
        //    });
        //}
        //System.in.read();
    }

}
