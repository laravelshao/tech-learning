package com.laravelshao.learning.zookeeper.rmi.rpc.client.zk.loadbalance;

import java.util.List;

/**
 * @author shaoqinghua
 * @date 2018/10/21
 * @description
 */
public abstract class AbstractLoadBalance implements LoadBalance {
    @Override
    public String selectHost(List<String> addressList) {
        if (addressList == null || addressList.size() == 0) {
            return null;
        }
        if (addressList.size() == 1) {
            return addressList.get(0);
        }

        return doSelect(addressList);
    }

    /**
     * 定义模板方法
     *
     * @param addressList
     * @return
     */
    protected abstract String doSelect(List<String> addressList);
}
