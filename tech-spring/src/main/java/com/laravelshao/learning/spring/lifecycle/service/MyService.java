package com.laravelshao.learning.spring.lifecycle.service;

import com.laravelshao.learning.spring.lifecycle.bean.Student;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/**
 * Created by shaoqinghua on 2018/8/27.
 */
public class MyService {

    private Student student;

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public MyService() {
        System.out.println("MyService->执行默认无参构造方法");
    }

    @PostConstruct
    public void init() {
        System.out.println("MyService->执行PostConstruct注解配置的初始化init方法");
    }

    @PreDestroy
    public void destory() {
        System.out.println("MyService->执行PreDestroy注解配置的销毁destory方法");
    }
}
