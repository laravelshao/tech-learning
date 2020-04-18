package com.laravelshao.learning.pattern.behavioral.templatemethod;

/**
 * @author qinghua.shao
 * @date 2018/8/6
 * @since 1.0.0
 */
public class Student extends AbstractPerson {

    @Override
    public void prepareGotoSchool() {
        super.prepareGotoSchool();
    }

    @Override
    protected void dressUp() {
        System.out.println("穿校服");
    }

    @Override
    protected void eatBreakfast() {
        System.out.println("吃妈妈做好的早饭");
    }

    @Override
    protected void takeThings() {
        System.out.println("背书包，带上家庭作业和红领巾");
    }
}
