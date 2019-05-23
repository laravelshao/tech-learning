package com.laravelshao.learning.zookeeper.rmi.rpc.client.zk;

/**
 * @author shaoqinghua
 * @date 2018/10/21
 * @description
 */
public interface ServiceDiscovery {

    /**
     * 根据服务名称获取服务地址
     *
     * @param serviceName
     * @return
     */
    String discovery(String serviceName);
}
