package com.laravelshao.learning.spring.di.annotation;

import org.springframework.stereotype.Repository;


@Repository("customerDAO")
public class CustomerDAO {
    public void save() {
        System.out.println("CustomerDAO->数据层save()方法被调用.....");
    }
}
