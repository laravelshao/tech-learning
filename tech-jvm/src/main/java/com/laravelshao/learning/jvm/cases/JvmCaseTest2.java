package com.laravelshao.learning.jvm.cases;

/**
 * 案例分析：模拟每日百亿数据量实时分析引擎定位和处理频繁Full GC
 *
 * <p>JVM Param: -Xms200m -Xmx200m -Xmn100m -XX:SurvivorRatio=8 -XX:MaxTenuringThreshold=15 -XX:PretenureSizeThreshold=20971520 -XX:+UseParNewGC -XX:+UseConcMarkSweepGC -XX:+PrintGCDetails -XX:+PrintGCTimeStamps
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
