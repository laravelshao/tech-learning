package com.laravelshao.learning.jvm.cases;

/**
 * 案例分析：模拟每秒10万并发BI系统定位和处理频繁 Young GC
 *
 * <p>JVM Param: -Xms20m -Xmx20m -Xmn10m -XX:SurvivorRatio=8 -XX:MaxTenuringThreshold=15 -XX:PretenureSizeThreshold=3145728 -XX:+UseParNewGC -XX:+UseConcMarkSweepGC -XX:+PrintGCDetails -XX:+PrintGCTimeStamps
 *
 * @author qinghua.shao
 * @date 2022/6/11
 * @since 1.0.0
 */
public class JvmCaseTest1 {

    public static void main(String[] args) throws InterruptedException {
        Thread.sleep(30000);
        while (true) {
            loadData();
        }
    }

    private static void loadData() throws InterruptedException {
        byte[] data = null;
        for (int i = 0; i < 50; i++) {
            data = new byte[100 * 1024];
        }
        data = null;

        Thread.sleep(1000);
    }
}
