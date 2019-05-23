package com.laravelshao.learning.dubbo.provider;

import com.laravelshao.learning.dubbo.api.HelloService;

/**
 * @author shaoqinghua
 * @date 2018/7/7
 * @description Hello服务实现
 */
public class HelloServiceImpl implements HelloService {

    @Override
    public String sayHello(String msg) {
        return "hello " + msg;
    }
}
