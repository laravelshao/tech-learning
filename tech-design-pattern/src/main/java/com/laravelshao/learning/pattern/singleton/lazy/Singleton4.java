package com.laravelshao.learning.pattern.singleton.lazy;

import java.io.*;

/**
 * 单例模式懒汉式：考虑线程安全(静态内部类实现)，考虑反射破坏单例特性，考虑序列化反序列化
 *
 * @author shaoqinghua
 * @date 2016/3/3
 * @description
 */
public class Singleton4 implements Serializable {

    /**
     * 定义一个初始标识
     */
    private static boolean initialized = false;

    private Singleton4() {
        synchronized (Singleton4.class) {
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
        private static final Singleton4 instance = new Singleton4();
    }

    public static Singleton4 getInstance() {
        return SingletonHolder.instance;
    }

    /**
     * readResolve方法会在ObjectInputStream已经读取一个对象并在准备返回前调用
     * ObjectInputStream会检查对象的class中是否定义了readResolve方法
     * 定义了将由readResolve方法指定返回的对象，返回对象的类型一定要是兼容的，否则会抛出ClassCastException
     *
     * @return
     */
    private Object readResolve() {
        return getInstance();
    }

    public static void main(String[] args) {
        try {
            Singleton4 instance1 = Singleton4.getInstance();
            ObjectOutput out;
            out = new ObjectOutputStream(new FileOutputStream("filename.ser"));
            out.writeObject(instance1);
            out.close();
            // 反序列化为对象
            ObjectInput in = new ObjectInputStream(new FileInputStream("filename.ser"));
            Singleton4 instance2 = (Singleton4) in.readObject();
            in.close();
            System.out.println("instance1 hashCode=" + instance1.hashCode());
            System.out.println("instance2 hashCode=" + instance2.hashCode());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
