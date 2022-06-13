package com.laravelshao.learning.jvm;

/**
 * JVM Young GC 测试
 *
 * <p>JVM Param: -Xms10m -Xmx10m -Xmn5m -XX:SurvivorRatio=8 -XX:+UseParNewGC -XX:+UseConcMarkSweepGC -XX:+PrintGCDetails -XX:+PrintGCTimeStamps
 *
 * @author qinghua.shao
 * @date 2022/6/3
 * @since 1.0.0
 */
public class JvmYoungGCTest {

    public static void main(String[] args) {
        byte[] array1 = new byte[1024 * 1024];
        array1 = new byte[1024 * 1024];
        array1 = new byte[1024 * 1024];
        array1 = null;

        byte[] array2 = new byte[2 * 1024 * 1024];
    }
}