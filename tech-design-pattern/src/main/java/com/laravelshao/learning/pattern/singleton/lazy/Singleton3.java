package com.laravelshao.learning.pattern.singleton.lazy;

import java.lang.reflect.Constructor;

/**
 * 单例模式懒汉式：考虑线程安全(静态内部类实现)，考虑反射破坏单例特性
 *
 * @author shaoqinghua
 * @date 2016/3/3
 * @description
 */
public class Singleton3 {

    /**
     * 定义一个初始标识
     */
    private static boolean initialized = false;

    private Singleton3() {
        synchronized (Singleton3.class) {
            if (initialized == false) {
                initialized = !initialized;
            } else {
                throw new RuntimeException("单例已被破坏");
            }
        }
    }

    /**
     * 静态内部类在第一次调用静态成员变量时才会被加载
     */
    static class SingletonHolder {
        private static final Singleton3 instance = new Singleton3();
    }

    public static Singleton3 getInstance() {
        return SingletonHolder.instance;
    }


    public static void main(String[] args) {
        // 创建第一个实例
        Singleton3 instance1 = Singleton3.getInstance();
        // 通过反射获取第二个实例
        Singleton3 instance2 = null;
        try {
            Class<Singleton3> clazz = Singleton3.class;
            Constructor<Singleton3> cons = clazz.getDeclaredConstructor();
            cons.setAccessible(true);
            instance2 = cons.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }

        // 检查两个实例的hash值
        System.out.println("instace 1 hash:" + instance1.hashCode());
        System.out.println("instace 2 hash:" + instance2.hashCode());
    }
}
