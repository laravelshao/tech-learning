package com.laravelshao.learning.pattern.creational.abstractfactory;

/**
 * Java课程工厂
 *
 * @author shaoqinghua
 * @date 2017/6/1
 * @description
 */
public class JavaCourseFactory implements CourseFactory {

    @Override
    public Video getVideo() {
        return new JavaVideo();
    }

    @Override
    public Note getNote() {
        return new JavaNote();
    }
}
