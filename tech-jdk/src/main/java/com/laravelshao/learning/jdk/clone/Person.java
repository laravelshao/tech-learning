package com.laravelshao.learning.jdk.clone;

import lombok.Data;

import java.io.*;

/**
 * @author shaoqinghua
 * @date 2019/1/6
 * @description
 */
@Data
public class Person implements Cloneable, Serializable {

    private String name;
    private int age;
    private Email email;

    public Person() {
    }

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    /**
     * 深克隆实现一：引用对象也实现Cloneable接口，重写clone方法
     *
     * @return
     * @throws CloneNotSupportedException
     */
    @Override
    protected Person clone() throws CloneNotSupportedException {
        Person person = (Person) super.clone();
        person.email = email.clone();
        return person;
    }

    /**
     * 深克隆实现二：序列化实现
     *
     * @return
     * @throws IOException
     * @throws ClassNotFoundException
     */
    protected Person deepClone() throws IOException, ClassNotFoundException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(baos);
        oos.writeObject(this);
        ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
        ObjectInputStream ois = new ObjectInputStream(bais);
        return (Person) ois.readObject();
    }

}
