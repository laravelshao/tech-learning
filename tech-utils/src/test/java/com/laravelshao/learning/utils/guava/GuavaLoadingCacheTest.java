package com.laravelshao.learning.utils.guava;

import com.google.common.base.Optional;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;
import org.junit.Test;

import java.time.LocalDateTime;

import static java.util.concurrent.TimeUnit.SECONDS;

/**
 * Guava 刷新缓存测试
 *
 * @author qinghua.shao
 * @date 2019/12/27
 * @since 1.0.0
 */
public class GuavaLoadingCacheTest {

    /**
     * 当前时间内存缓存
     */
    private static LoadingCache<String, Optional<String>> timeCache = CacheBuilder.newBuilder()
            .maximumSize(1000) // 缓存容量大小
            .refreshAfterWrite(5, SECONDS) // 刷新间隔5秒
            .expireAfterAccess(10, SECONDS) // 10秒内没有访问则过期
            .build(new CacheLoader<String, Optional<String>>() {

                @Override
                public Optional<String> load(String key) throws Exception {
                    System.out.println("首次加载 key => " + key + " 时间 => " + System.currentTimeMillis());
                    return Optional.fromNullable(LocalDateTime.now().toString());
                }

                @Override
                public ListenableFuture<Optional<String>> reload(final String key, Optional<String> oldValue) {
                    System.out.println("刷新加载 key => " + key + " 时间 => " + System.currentTimeMillis());
                    return Futures.immediateFuture(Optional.fromNullable(LocalDateTime.now().toString()));
                }
            });

    /**
     * 测试内存缓存
     *
     * @throws InterruptedException
     */
    @Test
    public void guavaLoadingCache() throws InterruptedException {

        for (int i = 0; i < 10; i++) {
            String time = timeCache.getUnchecked("1").orNull();
            System.out.println(time);
            Thread.sleep(1000);
        }

        Thread.sleep(11000); // 10秒内没有访问缓存将过期

        for (int i = 0; i < 5; i++) {
            String time = timeCache.getUnchecked("1").orNull(); // 第一次将重新加载
            System.out.println(time);
            Thread.sleep(1000);
        }
    }

}
