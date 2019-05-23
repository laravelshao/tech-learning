package com.laravelshao.learning.zookeeper.rmi.rpc.server;

import com.laravelshao.learning.zookeeper.rmi.rpc.server.annotation.RpcAnnotation;
import com.laravelshao.learning.zookeeper.rmi.rpc.server.zk.RegisterCenter;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author shaoqinghua
 * @date 2018/10/5
 * @description
 */
public class RpcServer {

    private static final ExecutorService executorService = Executors.newCachedThreadPool();

    private RegisterCenter registerCenter; // 注册中心

    private String serviceAddress; // 服务发布地址

    // 存放服务名称和服务对象对应关系
    Map<String, Object> handlerMap = new HashMap<>();

    public RpcServer(RegisterCenter registerCenter, String serviceAddress) {
        this.registerCenter = registerCenter;
        this.serviceAddress = serviceAddress;
    }

    /**
     * 绑定服务名称和服务对象
     *
     * @param services
     */
    public void bind(Object... services) {
        for (Object service : services) {
            RpcAnnotation rpcAnnotation = service.getClass().getAnnotation(RpcAnnotation.class);
            String serviceName = rpcAnnotation.value().getName();
            String version = rpcAnnotation.version();
            if (version != null && !version.equals("")) {
                serviceName = serviceName + "-" + version;
            }
            handlerMap.put(serviceName, service);
        }
    }

    public void publisher() {
        ServerSocket serverSocket = null;
        try {
            String[] parts = serviceAddress.split(":");
            serverSocket = new ServerSocket(Integer.parseInt(parts[1]));  // 启动一个服务监听
            for (String interfaceName : handlerMap.keySet()) {
                registerCenter.register(interfaceName, serviceAddress);
                System.out.println("服务注册成功->" + interfaceName + " " + serviceAddress);
            }

            // 循环监听
            while (true) {
                // 监听服务
                Socket socket = serverSocket.accept();
                // 使用线程池处理请求
                executorService.execute(new ProcessorHandler(socket, handlerMap));
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
