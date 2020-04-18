package com.laravelshao.learning.pattern.behavioral.templatemethod;

/**
 * 模板方法模式
 *
 * @author qinghua.shao
 * @date 2018/8/6
 * @since 1.0.0
 */
public class TemplateTest {

    public static void main(String[] args) {
        Student student = new Student();
        student.prepareGotoSchool();
        Teacher teacher = new Teacher();
        teacher.prepareGotoSchool();
    }
}
