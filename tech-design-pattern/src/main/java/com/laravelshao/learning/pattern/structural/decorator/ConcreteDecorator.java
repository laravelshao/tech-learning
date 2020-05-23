package com.laravelshao.learning.pattern.structural.decorator;

/**
 * 具体装饰角色
 *
 * @author qinghua.shao
 * @date 2020/5/23
 * @since 1.0.0
 */
class ConcreteDecorator extends Decorator {

    public ConcreteDecorator(Component component) {
        super(component);
    }

    public void operation() {
        super.operation();
        addedFunction();
    }

    public void addedFunction() {
        System.out.println("为具体构件角色增加额外的功能addedFunction()");
    }
}