package com.laravelshao.learning.pattern.creational.singleton.lazy;


/**
 * 单例模式懒汉式：考虑线程安全(双重检验锁实现，并考虑多线程内存可见性问题)
 *
 * @author shaoqinghua
 * @date 2016/3/3
 * @description
 */
public class Singleton1 {

    private Singleton1() {
    }

    /**
     * 需要使用 volatile 关键字，因为对于多线程情况下，其中一个线程加上同步锁后，其它线程将等待。
     * 当加锁成功线程执行完毕之后，会有其它线程进入执行，这个时候如果没有 volatile 修饰，那么上
     * 面获取到锁的线程实例化的对象，对于其它线程来说是不可见的，会导致重复实例化对象。
     */
    private static volatile Singleton1 instance = null;

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
