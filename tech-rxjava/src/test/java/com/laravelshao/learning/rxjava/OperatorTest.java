package com.laravelshao.learning.rxjava;

import io.reactivex.Observable;
import io.reactivex.internal.operators.observable.ObservableCountSingle;
import io.reactivex.internal.operators.observable.ObservableFilter;
import io.reactivex.internal.operators.observable.ObservableFromArray;
import io.reactivex.internal.operators.observable.ObservableMap;
import org.junit.Test;

/**
 * Observable 操作测试
 *
 * @author qinghua.shao
 * @date 2020/1/25
 * @since 1.0.0
 */
public class OperatorTest {

    /**
     * 月份数组
     */
    private String[] monthArray = {"Jan", "Feb", "Mar", "Apl", "Maly", "Jun", "July", "Aug", "Sept", "Oct", "Nov", "Dec"};

    /**
     * filter => 筛选过滤
     *
     * <p> {@link ObservableFilter.FilterObserver#onNext} 添加了过滤判断逻辑，另外因为使用
     * {@link Observable#fromArray}，最终会使用{@link ObservableFromArray.FromArrayDisposable#run} 方法，
     * 在该方法中会循环遍历调用前面的 #onNext 方法逻辑
     */
    @Test
    public void filter() {
        Observable.fromArray(monthArray)
                .filter(item -> item.contains("y"))
                .subscribe(item -> System.out.println(item),
                        Throwable::printStackTrace, () -> System.out.println("Completed normally"));
    }

    /**
     * count => 计数
     *
     * <p> {@link ObservableCountSingle.CountObserver#onNext} 添加了自增逻辑，只有在调用
     * {@link ObservableCountSingle.CountObserver#onComplete} 方法时才会调用我们定义的 #onSuccess 方法
     */
    @Test
    public void count() {
        Observable.fromArray(monthArray)
                .count()
                .subscribe(total -> System.out.println(total));
    }

    /**
     * map => 元素转换
     *
     * <p> {@link ObservableMap.MapObserver#onNext} 添加了元素转换逻辑
     */
    @Test
    public void map() {
        Observable.fromArray(monthArray)
                .map(String::toUpperCase)
                .subscribe(System.out::println);

        System.out.println("---------------------------");

        Observable.just(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
                .filter(i -> i % 3 > 0)
                .map(i -> "#" + i * 10)
                .filter(s -> s.length() < 4) // 排除#100
                .subscribe(System.out::println);
    }

    /**
     * flatMap => 扁平化元素转换
     *
     * <p>针对单值源的会进行特殊处理，利用 {@link ScalarXMapObservable} 进行包装
     *
     * @see 参考 JDK 中 {@link java.util.concurrent.Callable} 实现，通过
     * {@link java.util.concurrent.Executors.RunnableAdapter}对 {@link Runnable} 进行包装扩展，添加返回值
     */
    @Test
    public void flatMap() {
        Observable.range(1, 3)
                .flatMap(item -> Observable.range(item, 3))
                .subscribe(value -> System.out.print(value + "->"));
    }

    /**
     * scan => 累加处理
     */
    @Test
    public void scan() {
        Integer[] prices = {100, 200, 300, 15, 15};
        Observable.fromArray(prices)
                .scan((item1, item2) -> item1 + item2)
                .subscribe(integer -> System.out.println("amount: " + integer),
                        Throwable::printStackTrace, () -> System.out.println("Completed normally"));
    }

    /**
     * groupBy => 分组处理
     */
    @Test
    public void groupBy() {
        String[] monthArray = {"January", "Feb", "March", "Apl", "May", "Jun", "July", "Aug", "Sept", "Oct", "Nov", "Dec"};
        Observable.fromArray(monthArray)
                .groupBy(item -> item.length() <= 3 ? "THREE" : item.length() < 6 ? ">=4" : "DEFAULT")
                .subscribe(observable -> {
                    observable.subscribe(item -> log(item + ":" + observable.getKey()));
                    System.out.println("*********************************************");
                });
    }

    /**
     * merge => 合并多源元素
     */
    @Test
    public void merge() {
        Observable
                .merge(Observable.range(1, 5), Observable.range(10, 3))
                .subscribe(item -> System.out.println(item),
                        Throwable::printStackTrace, () -> System.out.println("completed"));
    }

    private static void log(Object msg) {
        System.out.println(Thread.currentThread().getName() + ": " + msg);
    }
}
