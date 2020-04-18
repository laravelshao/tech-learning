package com.laravelshao.learning.pattern.behavioral.observer;

import java.util.Observable;

/**
 * 继承 Observable 代表可被观察
 *
 * @author qinghua.shao
 * @date 2020/4/18
 * @since 1.0.0
 */
public class Course extends Observable {

    private String courseName;

    public Course(String courseName) {
        this.courseName = courseName;
    }

    public String getCourseName() {
        return courseName;
    }

    public void produceQuestion(Course course, Question question) {
        System.out.println(question.getUserName() + " 在 " + course.getCourseName() + " 提交了一个问题");
        setChanged(); // 修改状态
        notifyObservers(question); // 通知观察者
    }
}
