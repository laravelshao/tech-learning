package com.laravelshao.learning.zookeeper.rmi.rpc.server.zk;

/**
 * @author shaoqinghua
 * @date 2018/10/21
 * @description
 */
public interface RegisterCenter {

    /**
     * 注册服务名称和服务地址
     *
     * @param serviceName
     * @param serviceAddress
     */
    void register(String serviceName, String serviceAddress);
}
