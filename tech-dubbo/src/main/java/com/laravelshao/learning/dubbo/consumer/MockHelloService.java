package com.laravelshao.learning.dubbo.consumer;

import com.laravelshao.learning.dubbo.api.HelloService;

/**
 * @author shaoqinghua
 * @date 2018/7/7
 * @description 服务MOCK实现
 */
public class MockHelloService implements HelloService {

    @Override
    public String sayHello(String msg) {
        return "系统繁忙 " + msg;
    }
}
