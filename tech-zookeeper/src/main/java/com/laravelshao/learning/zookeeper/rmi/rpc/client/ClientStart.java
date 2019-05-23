package com.laravelshao.learning.zookeeper.rmi.rpc.client;


import com.laravelshao.learning.zookeeper.rmi.rpc.client.zk.ServiceDiscovery;
import com.laravelshao.learning.zookeeper.rmi.rpc.client.zk.ServiceDiscoveryImpl;
import com.laravelshao.learning.zookeeper.rmi.rpc.client.zk.ZkConfig;
import com.laravelshao.learning.zookeeper.rmi.rpc.service.HiService;

/**
 * @author shaoqinghua
 * @date 2018/10/5
 * @description
 */
public class ClientStart {

    public static void main(String[] args) {
        ServiceDiscovery serviceDiscovery = new ServiceDiscoveryImpl(ZkConfig.ZK_CONNECTION_URL);
        RpcClientProxy rpcClientProxy = new RpcClientProxy(serviceDiscovery);
        for (int i = 0; i < 10; i++) {
            HiService hiService = rpcClientProxy.clientProxy(HiService.class, null);
            System.out.println(hiService.sayHi("eva"));
        }
    }
}
