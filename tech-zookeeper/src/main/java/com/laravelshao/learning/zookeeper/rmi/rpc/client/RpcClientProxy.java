package com.laravelshao.learning.zookeeper.rmi.rpc.client;

import com.laravelshao.learning.zookeeper.rmi.rpc.client.zk.ServiceDiscovery;

import java.lang.reflect.Proxy;

/**
 * @author shaoqinghua
 * @date 2018/10/5
 * @description
 */
public class RpcClientProxy {

    private ServiceDiscovery serviceDiscovery;

    public RpcClientProxy(ServiceDiscovery serviceDiscovery) {
        this.serviceDiscovery = serviceDiscovery;
    }

    /**
     * 创建客户端的远程代理，通过远程代理进行访问
     *
     * @param interfaceClass
     * @param <T>
     * @return
     */
    public <T> T clientProxy(final Class<T> interfaceClass, String version) {
        return (T) Proxy.newProxyInstance(interfaceClass.getClassLoader(),
                new Class[]{interfaceClass}, new RemoteInvocationHandler(serviceDiscovery,version));
    }
}
