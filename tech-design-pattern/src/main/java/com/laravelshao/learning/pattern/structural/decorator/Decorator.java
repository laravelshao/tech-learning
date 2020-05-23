package com.laravelshao.learning.pattern.structural.decorator;

/**
 * 抽象装饰角色
 *
 * @author qinghua.shao
 * @date 2020/5/23
 * @since 1.0.0
 */
class Decorator implements Component {

    private Component component;

    public Decorator(Component component) {
        this.component = component;
    }

    public void operation() {
        component.operation();
    }
}