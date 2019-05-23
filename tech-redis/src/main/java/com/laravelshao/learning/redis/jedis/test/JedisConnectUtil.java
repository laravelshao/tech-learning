package com.laravelshao.learning.redis.jedis.test;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * @author shaoqinghua
 * @date 2019/2/7
 * @description
 */
public class JedisConnectUtil {

    private static JedisPool pool = null;

    static {
        JedisPoolConfig config = new JedisPoolConfig();
        config.setMaxTotal(100);
        pool = new JedisPool(config, "127.0.0.1", 6379);
    }

    /**
     * 获取Jedis连接
     *
     * @return
     */
    public static Jedis getJedis() {
        return pool.getResource();
    }
}
