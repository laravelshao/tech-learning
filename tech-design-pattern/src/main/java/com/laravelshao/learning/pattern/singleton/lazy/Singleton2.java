package com.laravelshao.learning.pattern.singleton.lazy;

/**
 * 单例模式懒汉式：考虑线程安全(静态内部类实现)
 *
 * @author shaoqinghua
 * @date 2016/3/3
 * @description
 */
public class Singleton2 {

    private Singleton2() {
    }

    /**
     * 静态内部类在第一次调用静态成员变量时才会被加载
     */
    static class SingletonHolder {
        private static final Singleton2 instance = new Singleton2();
    }

    public static Singleton2 getInstance() {
        return SingletonHolder.instance;
    }

    public static void main(String[] args) {

        System.out.println(Singleton2.getInstance());
        System.out.println(Singleton2.getInstance());
    }
}
