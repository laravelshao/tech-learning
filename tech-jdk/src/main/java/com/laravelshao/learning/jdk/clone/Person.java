package com.laravelshao.learning.jdk.clone;

import lombok.Data;

import java.io.*;

/**
 * @author qinghua.shao
 * @date 2019/1/6
 */
@Data
public class Person implements Cloneable, Serializable {
    private static final long serialVersionUID = -5229730289987180113L;

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
     * 深克隆实现方式1：引用对象也实现 Cloneable 接口，重写 clone 方法
     */
    @Override
    protected Person clone() throws CloneNotSupportedException {
        Person person = (Person) super.clone();
        person.email = email.clone();
        return person;
    }

    /**
     * 深克隆实现2：序列化实现
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
