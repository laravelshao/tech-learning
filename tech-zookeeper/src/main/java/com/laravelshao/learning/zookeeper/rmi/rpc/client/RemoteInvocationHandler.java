package com.laravelshao.learning.zookeeper.rmi.rpc.client;


import com.laravelshao.learning.zookeeper.rmi.rpc.client.zk.ServiceDiscovery;
import com.laravelshao.learning.zookeeper.rmi.rpc.service.RpcRequest;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @author shaoqinghua
 * @date 2018/10/5
 * @description
 */
public class RemoteInvocationHandler implements InvocationHandler {

    private ServiceDiscovery serviceDiscovery;

    private String version;

    public RemoteInvocationHandler(ServiceDiscovery serviceDiscovery, String version) {
        this.serviceDiscovery = serviceDiscovery;
        this.version = version;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        // 组装参数
        RpcRequest request = new RpcRequest();
        request.setClassName(method.getDeclaringClass().getName());
        request.setMethodName(method.getName());
        request.setParameters(args);
        request.setVersion(version);

        // 通过tcp传输协议进行传输
        String serviceAddress = serviceDiscovery.discovery(request.getClassName());
        TCPTransport tcpTransport = new TCPTransport(serviceAddress);
        // 发送请求
        return tcpTransport.send(request);
    }
}
