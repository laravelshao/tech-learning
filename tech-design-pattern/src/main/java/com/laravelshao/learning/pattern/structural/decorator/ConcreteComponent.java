package com.laravelshao.learning.pattern.structural.decorator;


/**
 * 具体构件角色
 *
 * @author qinghua.shao
 * @date 2020/5/23
 * @since 1.0.0
 */
class ConcreteComponent implements Component {

    public ConcreteComponent() {
        System.out.println("创建具体构件角色");
    }

    public void operation() {
        System.out.println("调用具体构件角色的方法operation()");
    }
}