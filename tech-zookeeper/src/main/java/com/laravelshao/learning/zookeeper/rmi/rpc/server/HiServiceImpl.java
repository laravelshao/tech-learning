package com.laravelshao.learning.zookeeper.rmi.rpc.server;


import com.laravelshao.learning.zookeeper.rmi.rpc.server.annotation.RpcAnnotation;
import com.laravelshao.learning.zookeeper.rmi.rpc.service.HiService;

/**
 * @author shaoqinghua
 * @date 2018/10/5
 * @description
 */
@RpcAnnotation(HiService.class)
public class HiServiceImpl implements HiService {

    @Override
    public String sayHi(String msg) {
        return "hello from port:8080 " + msg;
    }
}
