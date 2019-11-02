package com.laravelshao.learning.utils.utils.tuple;

/**
 * 三元组
 *
 * @author qinghua.shao
 * @date 2019/8/9
 * @since 1.2.1
 */
public class Tuple3<A, B, C> {
    public final A a;
    public final B b;
    public final C c;

    public Tuple3(A a, B b, C c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }
}
