package com.laravelshao.learning.spring.di.xml;

/**
 * Created by shaoqinghua on 2017/12/17.
 */
public class Car {

    private Integer id;
    private String name;
    private Double price;

    //构造器参数注入 必须提供一个有参构造方法
    public Car(Integer id, String name, Double price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Car{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                '}';
    }
}
