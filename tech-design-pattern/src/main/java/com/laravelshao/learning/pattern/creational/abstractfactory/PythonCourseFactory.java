package com.laravelshao.learning.pattern.creational.abstractfactory;

/**
 * Python课程工厂
 *
 * @author shaoqinghua
 * @date 2017/6/1
 * @description
 */
public class PythonCourseFactory implements CourseFactory {

    @Override
    public Video getVideo() {
        return new PythonVideo();
    }

    @Override
    public Note getNote() {
        return new PythonNote();
    }
}
