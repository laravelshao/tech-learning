package com.laravelshao.learning.rpc.manual.client;

import com.laravelshao.learning.rpc.manual.service.HiService;

/**
 * @author shaoqinghua
 * @date 2018/10/5
 * @description
 */
public class ClientStart {

    public static void main(String[] args) {
        RpcClientProxy rpcClientProxy = new RpcClientProxy();
        HiService hiService = rpcClientProxy.clientProxy(HiService.class, "localhost", 8888);
        System.out.println(hiService.sayHi("eva"));
    }
}
