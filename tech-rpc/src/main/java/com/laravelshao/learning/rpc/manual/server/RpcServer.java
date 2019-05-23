package com.laravelshao.learning.rpc.manual.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author shaoqinghua
 * @date 2018/10/5
 * @description
 */
public class RpcServer {

    private static final ExecutorService executorService = Executors.newCachedThreadPool();

    public void publisher(final Object service, int port) {
        ServerSocket serverSocket = null;
        try {
            // 启动一个服务监听
            serverSocket = new ServerSocket(port);
            // 循环监听
            while (true) {
                // 监听服务
                Socket socket = serverSocket.accept();
                // 使用线程池处理请求
                executorService.execute(new ProcessorHandler(socket, service));
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (serverSocket != null) {
                try {
                    serverSocket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
