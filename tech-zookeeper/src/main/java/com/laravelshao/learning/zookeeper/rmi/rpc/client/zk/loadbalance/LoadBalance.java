package com.laravelshao.learning.zookeeper.rmi.rpc.client.zk.loadbalance;

import java.util.List;

/**
 * @author shaoqinghua
 * @date 2018/10/21
 * @description
 */
public interface LoadBalance {

    String selectHost(List<String> addressList);
}
