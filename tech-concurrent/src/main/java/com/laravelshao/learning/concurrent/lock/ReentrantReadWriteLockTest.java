package com.laravelshao.learning.concurrent.lock;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author shaoqinghua
 * @date 2019/2/10
 * @description 读写锁测试(测试缓存读取及更新)
 */
public class ReentrantReadWriteLockTest {

    static Map<String, Object> cacheMap = new HashMap<>();

    static ReentrantReadWriteLock readWriteLock = new ReentrantReadWriteLock();

    static Lock read = readWriteLock.readLock(); // 读锁
    static Lock write = readWriteLock.writeLock(); // 写锁

    /**
     * 缓存读取
     *
     * @param key
     * @return
     */
    public static final Object get(String key) {
        read.lock(); // 读锁
        try {
            return cacheMap.get(key);
        } finally {
            read.unlock();
        }
    }

    /**
     * 缓存更新
     *
     * @param key
     * @param value
     */
    public static final void set(String key, Object value) {
        write.lock(); // 写锁
        try {
            cacheMap.put(key, value);
        } finally {
            write.unlock();
        }
    }

}
