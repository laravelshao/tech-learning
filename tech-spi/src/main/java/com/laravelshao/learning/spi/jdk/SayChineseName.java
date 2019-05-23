package com.laravelshao.learning.spi.jdk;

/**
 * Created by shaoqinghua on 2018/7/16.
 */
public class SayChineseName implements ISayName {
    @Override
    public void say() {
        System.out.println("哈哈哈");
    }
}
