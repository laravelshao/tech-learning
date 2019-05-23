package com.laravelshao.learning.zookeeper.rmi.rpc.server.zk;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.CreateMode;

/**
 * @author shaoqinghua
 * @date 2018/10/21
 * @description zookeeper实现带注册中心rpc框架
 */
public class RegisterCenterImpl implements RegisterCenter {

    private CuratorFramework curatorFramework;

    {
        curatorFramework = CuratorFrameworkFactory.builder().
                connectString(ZkConfig.ZK_CONNECTION_URL)
                .sessionTimeoutMs(10000)
                .retryPolicy(new ExponentialBackoffRetry(1000, 10)).build();
        curatorFramework.start();
    }

    @Override
    public void register(String serviceName, String serviceAddress) {
        // 注册服务
        String servicePath = ZkConfig.ZK_REGISTRY_PATH + "/" + serviceName;
        try {
            // 判断/registrys/hi-service是否存在 不存在则创建
            if (curatorFramework.checkExists().forPath(servicePath) == null) {
                curatorFramework.create().creatingParentsIfNeeded().forPath(servicePath, "0".getBytes());
            }
            String addressPath = servicePath + "/" + serviceAddress;
            String rsNode = curatorFramework.create()
                    .withMode(CreateMode.EPHEMERAL).forPath(addressPath, "0".getBytes());
            System.out.println("服务注册成功->" + rsNode);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
