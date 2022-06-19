package com.laravelshao.learning.concurrent;

import java.util.concurrent.TimeUnit;

/**
 * volatile 测试
 *
 * @author qinghua.shao
 * @date 2022/6/19
 * @since 1.0.0
 */
public class VolatileDemo {

    static volatile int flag = 0;

    public static void main(String[] args) {
        new Thread() {

            public void run() {
                int localFlag = flag;
                while (true) {
                    if (localFlag != flag) {
                        System.out.println("读取到了修改后的标志位：" + flag);
                        localFlag = flag;
                    }
                }
            }
        }.start();

        new Thread() {
            public void run() {
                int localFlag = flag;
                while (true) {
                    System.out.println("标志位被修改为了：" + ++localFlag);
                    flag = localFlag;
                    try {
                        TimeUnit.SECONDS.sleep(2);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }.start();
    }
}
