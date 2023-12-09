package com.laravelshao.learning.redis.redisson;

import org.redisson.Redisson;
import org.redisson.api.RBloomFilter;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;

/**
 * 布隆过滤器测试
 *
 * @author qinghua.shao
 * @date 2023/12/9
 * @since 1.0.0
 */
public class BloomFilterTest {

    public static void main(String[] args) {
        Config config = new Config();
        config.useSingleServer().setAddress("redis://localhost:6379");
        RedissonClient redissonClient = Redisson.create(config);

        RBloomFilter<String> bloomFilter = redissonClient.getBloomFilter("sample bloom filter");
        // initialize bloom filter with
        // expectedInsertions = 55000000
        // falseProbability = 0.03
        bloomFilter.tryInit(55000000L, 0.03);

        bloomFilter.add("keyValue1");
        bloomFilter.add("keyValue2");

        System.out.println("contains: keyValue2 " + bloomFilter.contains("keyValue2"));

        System.out.println("count: " + bloomFilter.count());
    }
}
