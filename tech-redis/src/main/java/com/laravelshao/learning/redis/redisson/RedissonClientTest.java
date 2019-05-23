package com.laravelshao.learning.redis.redisson;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;

/**
 * @author shaoqinghua
 * @date 2018/11/17
 * @description
 */
public class RedissonClientTest {

    public static void main(String[] args) {
        /**
         * 哨兵集群
         */
        //Config sentinelConfig = new Config();
        //String masterName = "mymaster";
        //sentinelConfig.useSentinelServers().addSentinelAddress("redis://172.16.105.128:26379").setMasterName(masterName);
        //RedissonClient redissonClient = Redisson.create(sentinelConfig);
        //redissonClient.getBucket("redissonSentinel").set("redissonSentinelTest");
        //System.out.println(redissonClient.getBucket("redissonSentinel").get());

        /**
         * redis cluster
         */

        Config clusterConfig = new Config();
        clusterConfig.useClusterServers()
                .addNodeAddress("redis://172.16.105.128:7001", "redis://172.16.105.129:7003");
        RedissonClient redissonClient = Redisson.create(clusterConfig);
        redissonClient.getBucket("redissonCluster").set("redissonClusterTest");
        System.out.println(redissonClient.getBucket("redissonCluster").get());
    }
}
