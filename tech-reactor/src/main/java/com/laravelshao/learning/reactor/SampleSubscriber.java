package com.laravelshao.learning.reactor;

import org.reactivestreams.Subscription;
import reactor.core.publisher.BaseSubscriber;

/**
 * BaseSubscriber 简单实现
 *
 * @author qinghua.shao
 * @date 2020/1/2
 * @since 1.0.0
 */
public class SampleSubscriber<T> extends BaseSubscriber<T> {

    @Override
    public void hookOnSubscribe(Subscription subscription) {
        System.out.println("Subscribed");
        request(1);
    }

    @Override
    public void hookOnNext(T value) {
        System.out.println(value);
        request(1);
    }


}