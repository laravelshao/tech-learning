package com.laravelshao.learning.rpc.manual.server;

import com.laravelshao.learning.rpc.manual.service.HiService;

/**
 * @author shaoqinghua
 * @date 2018/10/5
 * @description 启动RPC服务端
 */
public class ServerStart {

    public static void main(String[] args) {
        HiService hiService = new HiServiceImpl();
        RpcServer server = new RpcServer();
        server.publisher(hiService, 8888);
    }
}
