package com.laravelshao.learning.utils.utils.tuple;

/**
 * 三元组
 *
 * @author qinghua.shao
 * @date 2019/8/9
 * @since 1.0.0
 */
public class Tuple3<A, B, C> {
    public final A first;
    public final B second;
    public final C third;

    public Tuple3(A first, B second, C third) {
        this.first = first;
        this.second = second;
        this.third = third;
    }
}
