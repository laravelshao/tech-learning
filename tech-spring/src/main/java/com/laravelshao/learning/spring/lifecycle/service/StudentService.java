package com.laravelshao.learning.spring.lifecycle.service;

import com.laravelshao.learning.spring.lifecycle.bean.Student;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

/**
 * 不推荐这种方式耦合度高
 * Created by shaoqinghua on 2018/8/27.
 */
public class StudentService implements InitializingBean, DisposableBean {

    private Student student;

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public StudentService() {
        System.out.println("StudentService->执行默认无参构造方法");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("StudentService->执行InitializingBean接口的afterPropertiesSet方法");
    }

    @Override
    public void destroy() throws Exception {
        System.out.println("StudentService->执行DisposableBean接口的destroy方法");
    }
}
