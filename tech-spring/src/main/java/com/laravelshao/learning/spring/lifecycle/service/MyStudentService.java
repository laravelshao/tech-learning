package com.laravelshao.learning.spring.lifecycle.service;

import com.laravelshao.learning.spring.lifecycle.bean.Student;

/**
 * Created by shaoqinghua on 2018/8/27.
 */
public class MyStudentService {

    private Student student;

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public MyStudentService() {
        System.out.println("MyStudentService->执行默认无参构造方法");
    }

    public void init() {
        System.out.println("MyStudentService->执行xml配置的初始化init方法");
    }

    public void destory() {
        System.out.println("MyStudentService->执行xml配置的销毁destory方法");
    }
}
