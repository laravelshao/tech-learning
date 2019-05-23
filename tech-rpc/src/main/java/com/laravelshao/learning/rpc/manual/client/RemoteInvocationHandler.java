package com.laravelshao.learning.rpc.manual.client;

import com.laravelshao.learning.rpc.manual.service.RpcRequest;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @author shaoqinghua
 * @date 2018/10/5
 * @description
 */
public class RemoteInvocationHandler implements InvocationHandler {

    private String host;
    private int port;

    public RemoteInvocationHandler(String host, int port) {
        this.host = host;
        this.port = port;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        // 组装参数
        RpcRequest request = new RpcRequest();
        request.setClassName(method.getDeclaringClass().getName());
        request.setMethodName(method.getName());
        request.setParameters(args);

        // 通过tcp传输协议进行传输
        TCPTransport tcpTransport = new TCPTransport(this.host, this.port);
        // 发送请求
        return tcpTransport.send(request);
    }
}
