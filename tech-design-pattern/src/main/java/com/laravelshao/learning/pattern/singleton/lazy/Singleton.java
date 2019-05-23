package com.laravelshao.learning.pattern.singleton.lazy;

/**
 * 单例模式懒汉式：原始实现(存在多线程安全问题)
 *
 * @author shaoqinghua
 * @date 2016/3/3
 * @description
 */
public class Singleton {

    private Singleton() {
    }

    private static Singleton instance = null;

    public static Singleton getInstance() {
        if (instance == null) {
            instance = new Singleton();
        }
        return instance;
    }

    public static void main(String[] args) {

        new Thread(() -> System.out.println(Singleton.getInstance())).start();
        new Thread(() -> System.out.println(Singleton.getInstance())).start();
    }
}
