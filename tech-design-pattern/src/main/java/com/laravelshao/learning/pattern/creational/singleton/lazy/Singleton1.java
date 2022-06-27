package com.laravelshao.learning.pattern.creational.singleton.lazy;


/**
 * 单例模式懒汉式：考虑线程安全(双重检验锁实现，并考虑指令重排序问题)
 *
 * @author shaoqinghua
 * @date 2016/3/3
 * @description
 */
public class Singleton1 {

    private Singleton1() {
    }

    /**
     * 需要使用 volatile 关键字，解决的不是可见性问题，而是指令重排问题，单例对象实例化时，
     * synchronized 可以保证立马刷入主内存，其它线程也能强制从主内存中加载。
     * <p>
     * 实例化对象包含：
     * 1.分配对象的内存空间
     * 2.初始化对象
     * 3.设置 instance 指向刚分配的内存地址
     * <p>
     * 指令重排之后可能会出现：步骤3在步骤2前面
     * 当 线程A 执行到同步代码块内，发生了指令重排序，先执行将对象的引用赋值给了 static instance，
     * 那么此时 线程B 执行到同步代码块外面的 if 判断，就会发现 instance 不为 null，就继续执行返回，
     * 但此时 线程A 还未将构造方法初始完毕。
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
