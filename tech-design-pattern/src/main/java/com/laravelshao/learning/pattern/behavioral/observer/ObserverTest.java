package com.laravelshao.learning.pattern.behavioral.observer;

/**
 * 观察者模式测试
 *
 * @author qinghua.shao
 * @date 2020/4/18
 * @since 1.0.0
 */
public class ObserverTest extends Question {

    public static void main(String[] args) {
        Course course = new Course("大疆航拍学习");
        Teacher teacher = new Teacher("小悟");
        Teacher teacher2 = new Teacher("小御");
        course.addObserver(teacher);
        course.addObserver(teacher2);

        Question question = new Question();
        question.setUserName("haha");
        question.setQuestionContent("如何刷锅");

        course.produceQuestion(course,question);
    }
}
