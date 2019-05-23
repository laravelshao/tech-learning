package com.laravelshao.learning.pattern.singleton.hungry;

/**
 * 单例模式饿汉式：类在加载时直接创建对象，不存在线程安全问题
 *
 * @author shaoqinghua
 * @date 2016/3/3
 * @description
 */
public class Singleton {

    private Singleton() {
    }

    private static Singleton instance = new Singleton();

    public static Singleton getInstance() {
        return instance;
    }

    public static void main(String[] args) {
        System.out.println(Singleton.getInstance());
        System.out.println(Singleton.getInstance());
    }
}
