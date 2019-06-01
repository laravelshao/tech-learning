package com.laravelshao.learning.pattern.creational.singleton.lazy;


/**
 * 单例模式懒汉式：考虑线程安全(双重检验锁实现)
 *
 * @author shaoqinghua
 * @date 2016/3/3
 * @description
 */
public class Singleton1 {

    private Singleton1() {
    }

    private static Singleton1 instance = null;

    public static Singleton1 getInstance() {
        /*
         * 外层的判断的目的是为后续其他线程在获取单例对象的时候提供效率，
         * 后续其他线程再调用getInstance方法的时候，由于instance变量中已经有对象
         * 那么判断就不会成立，线程就不用进入到同步中。更不会判断同步的锁
         */
        if (instance == null) {
            // 判断和赋值有安全问题，需要使用同步
            synchronized (Singleton1.class) {
                if (instance == null) {
                    instance = new Singleton1();
                }
            }
        }
        return instance;
    }

    public static void main(String[] args) {

        new Thread(() -> System.out.println(Singleton1.getInstance())).start();
        new Thread(() -> System.out.println(Singleton1.getInstance())).start();
    }
}
