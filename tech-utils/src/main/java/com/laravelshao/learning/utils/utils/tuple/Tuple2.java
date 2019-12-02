package com.laravelshao.learning.utils.utils.tuple;

/**
 * 二元组
 *
 * @author qinghua.shao
 * @date 2019/8/9
 * @since 1.0.0
 */
public class Tuple2<A, B> {
    public final A first;
    public final B second;

    public Tuple2(A first, B second) {
        this.first = first;
        this.second = second;
    }
}