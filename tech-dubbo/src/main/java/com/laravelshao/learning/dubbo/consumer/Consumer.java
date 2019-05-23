package com.laravelshao.learning.dubbo.consumer;

import com.laravelshao.learning.dubbo.api.HelloService;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author shaoqinghua
 * @date 2018/7/7
 * @description 服务消费者
 */
public class Consumer {

    public static void main(String[] args) throws InterruptedException {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
                new String[]{"/META-INF/spring/dubbo-consumer.xml"});
        HelloService helloService = context.getBean(HelloService.class);

        System.out.println(helloService.sayHello("哈哈哈哈"));

    }
}
