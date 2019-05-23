package com.laravelshao.learning.redis.jedis;

import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisSentinelPool;

import java.util.HashSet;
import java.util.Set;

/**
 * @author shaoqinghua
 * @date 2018/11/12
 * @description
 */
public class JedisClientTest {

    public static void main(String[] args) {

        /**
         * 连接哨兵集群(客户端应该连接到哨兵集群地址)
         * 保护模式不允许连接哨兵集群 可修改哨兵配置文件sentinel.conf中protected-mode为no
         */
        //String masterName = "mymaster"; // 自定义master名称
        //Set<String> sentinels = new HashSet<>(); // 哨兵集群地址
        //sentinels.add("172.16.105.128:26379");
        //JedisSentinelPool jedisSentinelPool = new JedisSentinelPool(masterName, sentinels);
        //Jedis jedis = jedisSentinelPool.getResource();
        //String setResult = jedis.set("jedisSentinel", "jedisSentinelTest");
        //System.out.println("set->" + setResult);
        //System.out.println("get->" + jedis.get("jedisSentinel"));

        /**
         * redis cluster
         */
        Set<HostAndPort> hostAndPortSet = new HashSet<>();
        hostAndPortSet.add(new HostAndPort("172.16.105.128", 7001));
        hostAndPortSet.add(new HostAndPort("172.16.105.129", 7003));
        JedisCluster jedisCluster = new JedisCluster(hostAndPortSet);
        String setR = jedisCluster.set("jedisCluster", "jedisClusterTest");
        System.out.println("set->" + setR);
        System.out.println("get->" + jedisCluster.get("jedisCluster"));

    }
}
