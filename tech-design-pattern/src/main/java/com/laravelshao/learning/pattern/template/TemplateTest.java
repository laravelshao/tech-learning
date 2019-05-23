package com.laravelshao.learning.pattern.template;

/**
 * 模板方法模式
 * Created by shaoqinghua on 2018/8/6.
 */
public class TemplateTest {

    public static void main(String[] args) {
        Student student = new Student();
        student.prepareGotoSchool();
        Teacher teacher = new Teacher();
        teacher.prepareGotoSchool();
    }
}
