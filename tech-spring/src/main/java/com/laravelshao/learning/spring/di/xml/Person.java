package com.laravelshao.learning.spring.di.xml;

/**
 * Created by shaoqinghua on 2017/12/17.
 */
public class Person {
    private Integer id;
    private String pname;
    private Car car;

    //必须提供setter方法
    public void setId(Integer id) {
        this.id = id;
    }

    public void setName(String name) {
        this.pname = name;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", pname='" + pname + '\'' +
                ", car=" + car +
                '}';
    }
}
