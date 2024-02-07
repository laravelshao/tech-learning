package com.laravelshao.learning.jdk.clone;

import java.io.IOException;

/**
 * 浅克隆与深克隆
 * <p>
 * 浅克隆：被复制对象的所有变量都含有与原来的对象相同的值，而所有的对其他
 * 对象的引用仍然指向原来的对象。
 * 实现：实现Cloneable接口，重写clone方法
 * <p>
 * 深克隆：被复制对象的所有变量都含有与原来的对象相同的值，除去那些引用
 * 其他对象的变量。那些引用其他对象的变量将指向被复制过的新对象，
 * 而不再是原有的那些被引用的对象。换言之，深拷贝把要复制的对象所引用的对象都复制了一遍。
 *
 * @author shaoqinghua
 * @date 2019/1/6
 * @description
 */
public class CloneTest {

    public static void main(String[] args) throws CloneNotSupportedException, IOException, ClassNotFoundException {

        Email email = new Email();
        email.setContent("明天上课");
        Person p1 = new Person("haha", 18);
        p1.setEmail(email);

        //Person p2 = p1.clone();
        Person p2 = p1.deepClone();
        p2.setName("xiaoai");
        p2.setAge(23);
        p2.getEmail().setContent("明天不用上课");

        System.out.println(p1);
        System.out.println(p2);
    }
}
