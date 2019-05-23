package com.laravelshao.learning.zookeeper.rmi.rpc.client.zk.loadbalance;

import java.util.List;
import java.util.Random;

/**
 * @author shaoqinghua
 * @date 2018/10/21
 * @description 随机负载均衡实现
 */
public class RandomLoadBalance extends AbstractLoadBalance {

    @Override
    protected String doSelect(List<String> addressList) {
        Random random = new Random();
        return addressList.get(random.nextInt(addressList.size()));
    }
}
