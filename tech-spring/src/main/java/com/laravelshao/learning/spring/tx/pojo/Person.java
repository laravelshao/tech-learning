package com.laravelshao.learning.spring.tx.pojo;

import lombok.Data;

/**
 * Created by shaoqinghua on 2018/9/6.
 */
@Data
public class Person {
    public Person() {
    }

    public Person(String name) {
        this.name = name;
    }

    private Integer id;
    private String name;

}
