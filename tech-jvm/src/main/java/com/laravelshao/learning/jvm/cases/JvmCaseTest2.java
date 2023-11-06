package com.laravelshao.learning.jvm.cases;

/**
 * 案例分析：模拟每日百亿数据量实时分析引擎定位和处理频繁Full GC
 * <p>
 * 步骤：
 * 1. jps: 查询 PID
 * 2. jstat -gc 70509 500 1000: 查看 JVM 内存和 GC 情况
 * <p>
 * 优化前 JVM Param: -Xms200m -Xmx200m -Xmn100m -XX:SurvivorRatio=8 -XX:MaxTenuringThreshold=15 -XX:PretenureSizeThreshold=20971520 -XX:+UseParNewGC -XX:+UseConcMarkSweepGC -XX:+PrintGCDetails -XX:+PrintGCTimeStamps
 * 说明：堆区占 200m，新生代占 100m，老年代占 100m，Eden:S0:S1=8:1:1，即 Eden 80m，S0=S1=10m，大对象阈值 20m。
 * <p>
 * 优化思路：因为每次 Young GC 存活对象 30m 大于 s0/s1 的10m，则会直接进入老年代，而老年代平均经过3次 Young GC 后会进行 Full GC。需要保证 s0/s1 有足够空间容纳存活对象。
 * 因此可以设置堆区占 300m，新生代占 200m，老年代占 100m，Eden:S0:S1=2:1:1，即 Eden 100m，S0=S1=50m。
 * 优化后 JVM Param: -Xms300m -Xmx300m -Xmn200m -XX:SurvivorRatio=2 -XX:MaxTenuringThreshold=15 -XX:PretenureSizeThreshold=20971520 -XX:+UseParNewGC -XX:+UseConcMarkSweepGC -XX:+PrintGCDetails -XX:+PrintGCTimeStamps
 *
 * @author qinghua.shao
 * @date 2022/6/11
 * @since 1.0.0
 */
public class JvmCaseTest2 {

    /**
     * 模拟说明：
     * 每秒种都会执行一次 loadData() 方法，会分配4个10MB的数组，但是都立马成为垃圾；但是会有 data1 和 data2两个10MB
     * 的数组是被变量引用必须存活的，此时 Eden 区已经占用60MB，接着 data3 变量一次指向两个10MB的数组，从而在1秒内触发
     * Young GC。
     */
    public static void main(String[] args) throws InterruptedException {
        Thread.sleep(30000);
        while (true) {
            loadData();
        }
    }

    private static void loadData() throws InterruptedException {
        byte[] data = null;
        for (int i = 0; i < 4; i++) {
            data = new byte[10 * 1024 * 1024];
        }
        data = null;

        byte[] data1 = new byte[10 * 1024 * 1024];
        byte[] data2 = new byte[10 * 1024 * 1024];

        byte[] data3 = new byte[10 * 1024 * 1024];
        data3 = new byte[10 * 1024 * 1024];

        Thread.sleep(1000);
    }
}
