package com.laravelshao.learning.utils.utils.tuple;

/**
 * 二元组
 *
 * @author qinghua.shao
 * @date 2019/8/9
 * @since 1.2.1
 */
public class Tuple2<A, B> {
    public final A a;
    public final B b;

    public Tuple2(A a, B b) {
        this.a = a;
        this.b = b;
    }
}