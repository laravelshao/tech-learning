package com.laravelshao.learning.redis.jedis;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * @author shaoqinghua
 * @date 2018/11/17
 * @description
 */
public class JedisUtil {

    private static JedisPool pool;

    static {
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        jedisPoolConfig.setMaxTotal(100);
        pool = new JedisPool(jedisPoolConfig, "172.16.105.130", 6379);
    }

    public static Jedis getJedis() {
        return pool.getResource();
    }
}
