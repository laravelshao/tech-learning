package com.laravelshao.learning.dubbo.provider;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;

/**
 * @author shaoqinghua
 * @date 2018/7/7
 * @description 服务提供者
 */
public class Provider {

    public static void main(String[] args) throws IOException {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
                new String[]{"/META-INF/spring/dubbo-provider.xml"});
        context.start();


        //Protocol extension = ExtensionLoader.getExtensionLoader(Protocol.class).getAdaptiveExtension();
        System.in.read();
    }
}
