package com.laravelshao.learning.rxjava;

import io.reactivex.Observable;
import org.junit.Test;

/**
 * Observable 工厂方法测试
 *
 * @author qinghua.shao
 * @date 2020/1/24
 * @since 1.0.0
 */
public class ObservableTest {

    /**
     * just => 接收1-10个元素，用于给订阅者发送元素
     */
    @Test
    public void observableJust() {
        Observable<String> observable = Observable.just("hello", "world", "one", "two");
        observable.subscribe(System.out::println);
    }

    /**
     * fromXXX => 将数组、Iterable、Future的元素用于发送
     */
    @Test
    public void observableFrom() {
        Observable<String> from = Observable.fromArray("one", "two", "three", "four");
        from.subscribe(System.out::println);
    }

    /**
     * range => 创建一个指定范围的Observable
     */
    @Test
    public void observableRange() {
        Observable<Integer> range = Observable.range(6, 4);
        range.subscribe(System.out::println);
    }

    /**
     * empty => 创建一个空的不发送元素但可以正常结束的Observable
     */
    @Test
    public void observableEmpty() {
        Observable<Object> empty = Observable.empty();
        empty.subscribe(System.out::println, Throwable::printStackTrace,
                () -> System.out.println("I am done!! Completed normally"));
    }

    /**
     * defer => 延迟生成Observable对象，只有在订阅者订阅时才创建，可理解为用来返回Observable对象的工厂方法
     */
    @Test
    public void observableDefer() {
        String[] monthArray = {"Jan", "Feb", "Mar", "Apl", "May", "Jun", "July", "Aug", "Sept", "Oct", "Nov", "Dec"};
        Observable.defer(() -> {
            return Observable.fromArray(monthArray);
        }).subscribe(System.out::println, Throwable::printStackTrace, () -> System.out.println("Emission completed"));
    }

    /**
     * never => 不发出任何元素，包括正常值、结束信息、错误信息都不会发出，仅用于测试
     */
    @Test
    public void observableNever() {
        Observable.never()
                .subscribe(System.out::println, Throwable::printStackTrace, () -> System.out.println("completed"));
    }

    /**
     * error => 订阅时会立即调用onError发出异常，通知每一位订阅者，不会发出其它信息，也不会调用onComplete方法发出正常的结束通知
     */
    @Test
    public void observableError() {
        Observable.error(new Exception("got an Exception"))
                .subscribe(System.out::println, Throwable::printStackTrace, () -> System.out.println("completed normally"));
    }

    /**
     * cache => 可缓存Observable
     */
    @Test
    public void observableCache() {
        Observable<Object> observable = Observable.create(observer -> {
            observer.onNext("处理的数字是：" + Math.random() * 100);
            observer.onComplete();
        })  .cache();

        observable.subscribe(consumer -> {
            System.out.println("我处理的元素是：" + consumer);
        });
        observable.subscribe(consumer -> {
            System.out.println("我处理的元素是：" + consumer);
        });
    }

}
