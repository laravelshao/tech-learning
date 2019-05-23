package com.laravelshao.learning.zookeeper.rmi.rpc.server;


import com.laravelshao.learning.zookeeper.rmi.rpc.server.zk.RegisterCenter;
import com.laravelshao.learning.zookeeper.rmi.rpc.server.zk.RegisterCenterImpl;
import com.laravelshao.learning.zookeeper.rmi.rpc.service.HiService;

import java.io.IOException;

/**
 * @author shaoqinghua
 * @date 2018/10/5
 * @description 启动RPC服务端
 */
public class ServerStart {

    public static void main(String[] args) throws IOException {
        HiService hiService = new HiServiceImpl();
        HiServiceImplV2 hiServiceV2 = new HiServiceImplV2();
        RegisterCenter registerCenter = new RegisterCenterImpl();
        RpcServer server = new RpcServer(registerCenter, "127.0.0.1:8080");
        server.bind(hiService, hiServiceV2);
        server.publisher();
        System.in.read();
    }
}
