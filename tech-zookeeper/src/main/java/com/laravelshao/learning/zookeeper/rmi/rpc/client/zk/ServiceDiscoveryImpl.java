package com.laravelshao.learning.zookeeper.rmi.rpc.client.zk;

import com.laravelshao.learning.zookeeper.rmi.rpc.client.zk.loadbalance.LoadBalance;
import com.laravelshao.learning.zookeeper.rmi.rpc.client.zk.loadbalance.RandomLoadBalance;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.cache.PathChildrenCache;
import org.apache.curator.framework.recipes.cache.PathChildrenCacheEvent;
import org.apache.curator.framework.recipes.cache.PathChildrenCacheListener;
import org.apache.curator.retry.ExponentialBackoffRetry;

import java.util.List;

/**
 * @author shaoqinghua
 * @date 2018/10/21
 * @description
 */
public class ServiceDiscoveryImpl implements ServiceDiscovery {

    private CuratorFramework curatorFramework;

    private List<String> availableServiceList;
    private String zkAddress;

    public ServiceDiscoveryImpl(String zkAddress) {
        this.zkAddress = zkAddress;
        curatorFramework = CuratorFrameworkFactory.builder().
                connectString(zkAddress)
                .sessionTimeoutMs(10000)
                .retryPolicy(new ExponentialBackoffRetry(1000, 10)).build();
        curatorFramework.start();
    }

    @Override
    public String discovery(String serviceName) {
        String path = ZkConfig.ZK_REGISTRY_PATH + "/" + serviceName;
        try {
            availableServiceList = curatorFramework.getChildren().forPath(path);
        } catch (Exception e) {
            throw new RuntimeException("获取子节点异常：" + e);
        }

        // 动态发现服务节点变化
        registerWatcher(path);

        // 负载均衡
        LoadBalance loadBalance = new RandomLoadBalance();
        return loadBalance.selectHost(availableServiceList); // 返回调用服务地址
    }

    /**
     * 注册watcher监听(动态刷新可用服务列表)
     */
    private void registerWatcher(String path) {
        PathChildrenCache pathChildrenCache = new PathChildrenCache(curatorFramework, path, true);
        PathChildrenCacheListener pathChildrenCacheListener = new PathChildrenCacheListener() {
            @Override
            public void childEvent(CuratorFramework curatorFramework, PathChildrenCacheEvent pathChildrenCacheEvent) throws Exception {
                availableServiceList = curatorFramework.getChildren().forPath(path);
            }
        };
        pathChildrenCache.getListenable().addListener(pathChildrenCacheListener);
        try {
            pathChildrenCache.start();
        } catch (Exception e) {
            throw new RuntimeException("注册PathChild Watcher 异常" + e);
        }
    }

}
