package com.laravelshao.learning.rpc.manual.client;

import java.lang.reflect.Proxy;

/**
 * @author shaoqinghua
 * @date 2018/10/5
 * @description
 */
public class RpcClientProxy {

    /**
     * 创建客户端的远程代理，通过远程代理进行访问
     *
     * @param interfaceClass
     * @param host
     * @param port
     * @param <T>
     * @return
     */
    public <T> T clientProxy(final Class<T> interfaceClass,
                             final String host, final int port) {
        return (T) Proxy.newProxyInstance(interfaceClass.getClassLoader(),
                new Class[]{interfaceClass}, new RemoteInvocationHandler(host, port));
    }
}
