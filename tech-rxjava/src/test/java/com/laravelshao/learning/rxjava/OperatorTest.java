package com.laravelshao.learning.rxjava;

import com.google.common.collect.ImmutableList;
import io.reactivex.Observable;
import io.reactivex.ObservableTransformer;
import io.reactivex.internal.operators.observable.ObservableCountSingle;
import io.reactivex.internal.operators.observable.ObservableFilter;
import io.reactivex.internal.operators.observable.ObservableFromArray;
import io.reactivex.internal.operators.observable.ObservableMap;
import org.junit.Test;

import java.math.BigInteger;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static io.reactivex.Observable.interval;

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
     * scan => 累加处理(同类型)
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
     * scan => 累加处理(不同类型)
     */
    @Test
    public void scan2() {
        Observable<BigInteger> scan2 = Observable
                .range(2, 10)
                .scan(BigInteger.ONE, (big, cur) -> big.multiply(BigInteger.valueOf(cur)));
        scan2.forEach(System.out::println);
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
        Observable.merge(Observable.range(1, 5), Observable.range(10, 3))
                .subscribe(item -> System.out.println(item),
                        Throwable::printStackTrace, () -> System.out.println("completed"));
    }

    /**
     * zip => 细粒度合并多源元素
     */
    @Test
    public void zip() {
        Integer[] numbers = {1, 2, 13, 34, 15, 17};
        String[] fruits = {"苹果", "梨", "李子", "荔枝", "芒果"};

        Observable<Integer> source1 = Observable.fromArray(numbers);
        Observable<String> source2 = Observable.fromArray(fruits);
        Observable<Integer> source3 = Observable.range(10, 3);

        Observable.zip(source1, source2, source3, (value1, value2, value3) -> value1 + "=>" + value2 + "=>" + value3)
                .subscribe(item -> System.out.println(item),
                        Throwable::printStackTrace, () -> System.out.println("completed"));
    }

    /**
     * combineLatest => 针对其中一个源，其发出的某一个元素只找离得最近的元素进行合并
     */
    @Test
    public void combineLatest() {
        // 测试1
        Integer[] numbers = {1, 2, 13, 34, 15, 17};
        String[] fruits = {"苹果", "梨", "李子", "荔枝", "芒果"};

        Observable<Integer> source1 = Observable.fromArray(numbers);
        Observable<String> source2 = Observable.fromArray(fruits);
        Observable.combineLatest(source2, source1, (item1, item2) -> item1 + item2)
                .subscribe(item -> System.out.println(item),
                        Throwable::printStackTrace, () -> System.out.println("completed"));

        System.out.println("---------------------------");

        // 测试2
        Observable.combineLatest(
                interval(2, TimeUnit.SECONDS).map(x -> "Java" + x),
                interval(1, TimeUnit.SECONDS).map(x -> "Spring" + x),
                (s, f) -> f + ":" + s
        ).forEach(System.out::println);

        sleep(6, TimeUnit.SECONDS);
    }

    /**
     * withLatestFrom
     */
    @Test
    public void withLatestFrom() {
        Integer[] numbers = {1, 2, 13, 34, 15, 17};
        String[] fruits = {"苹果", "梨", "李子", "荔枝", "芒果"};
        // 此处加入delay通过对第一次发送元素的打的时间差来确定withLatestFrom带来的差异，以确定书中此处的论断
        Observable<Integer> source1 = Observable.fromArray(numbers).delay(2, TimeUnit.MILLISECONDS);
        Observable<String> source2 = Observable.fromArray(fruits).delay(3, TimeUnit.MILLISECONDS);
        source1.withLatestFrom(source2, (item1, item2) -> item1 + item2)
                .forEach(System.out::println);

        System.out.println("---------------------------");

        Observable<String> stringObservable1 = interval(2, TimeUnit.SECONDS).map(x -> "Java" + x);
        Observable<String> stringObservable2 = interval(1, TimeUnit.SECONDS).map(x -> "Spring" + x);

        stringObservable2
                .withLatestFrom(stringObservable1, (s, f) -> s + ":" + f)
                .forEach(System.out::println);

        sleep(6, TimeUnit.SECONDS);
    }

    /**
     * amb => 获取最新发送消息的源，忽略其他源
     */
    @Test
    public void amb() {
        Integer[] numbers = {1, 2, 13, 34, 15, 17};
        String[] fruits = {"苹果", "梨", "李子", "荔枝", "芒果"};

        Observable<Integer> source1 = Observable.fromArray(numbers).delay(1, TimeUnit.SECONDS);
        Observable<String> source2 = Observable.fromArray(fruits);
        Observable.ambArray(source1, source2)
                .forEach(System.out::println);
    }

    /**
     * reduce => 聚合操作
     */
    @Test
    public void reduce() {
        Observable.range(10, 20)
                .reduce(new ArrayList<>(),
                        (list, item) -> {
                            list.add(item);
                            return list;
                        }).subscribe(System.out::println);

        System.out.println("---------------------------");

        Observable.range(10, 20)
                .reduce(BigInteger.ONE, (big, cur) -> big.multiply(BigInteger.valueOf(cur)))
                .subscribe(System.out::println);
    }

    /**
     * collect => 收集操作
     */
    @Test
    public void collect() {
        Observable.range(10, 20)
                .collect(ArrayList::new, List::add)
                .subscribe(System.out::println);
    }

    /**
     * distinct => 去重操作
     */
    @Test
    public void distinct() {

        Observable<LocalDate> dates = Observable.just(
                LocalDate.of(2018, 1, 3), LocalDate.of(2018, 3, 4), LocalDate.of(2018, 1, 5), LocalDate.of(2018, 11, 3));

        // 根据月份去重获取月份
        dates.map(LocalDate::getMonth)
                .distinct()
                .subscribe(System.out::println);

        System.out.println("---------------------------");

        // 根据月份去重获取天
        dates.distinct(LocalDate::getMonth)
                .subscribe(System.out::println);
    }

    /**
     * distinctUntilChanged => 过滤重复数据
     */
    @Test
    public void distinctUntilChanged() {
        // 过滤重复数字
        Observable.just(1, 1, 1, 2, 2, 3, 3, 2, 1, 1)
                .distinctUntilChanged()
                .subscribe(i -> System.out.println("RECEIVED: " + i));

        System.out.println("---------------------------");

        // 过滤长度相同字符串
        Observable.just("Apple", "Orange", "Appla", "Eatla", "HOHO", "Meta")
                .distinctUntilChanged(String::length)
                .subscribe(i -> System.out.println("RECEIVED: " + i));
    }

    /**
     * take(n) => 获取前n个元素
     * skip(n) => 忽略前n个元素
     * takeLast(n) => 获取最后n个元素
     * skipLast(n) => 忽略最后n个元素
     * takeUntil(predicate) => 获取符合条件之前及符合条件的元素
     * takeWhile(predicate) => 获取符合条件的元素
     * all(predicate) => 判断所有元素都满足条件
     * contains => 是否包含该元素
     */
    @Test
    public void takeOrSkip() {
        Observable<Integer> range = Observable.range(1, 5);

        range.take(3)
                .collect(ArrayList::new, List::add)
                .subscribe(System.out::println);  // [1, 2, 3]
        range.skip(3)
                .collect(ArrayList::new, List::add)
                .subscribe(System.out::println);  // [4, 5]
        range.skip(5)
                .collect(ArrayList::new, List::add)
                .subscribe(System.out::println);  // []

        range.takeLast(2)
                .collect(ArrayList::new, List::add)
                .subscribe(System.out::println);  // [4, 5]
        range.skipLast(2)
                .collect(ArrayList::new, List::add)
                .subscribe(System.out::println);  // [1, 2, 3]

        range.takeUntil(x -> x == 3)
                .collect(ArrayList::new, List::add)
                .subscribe(System.out::println);  // [1, 2, 3]
        range.takeWhile(x -> x != 3)
                .collect(ArrayList::new, List::add)
                .subscribe(System.out::println);  // [1, 2]

        range.all(x -> x != 4)
                .subscribe(System.out::println);    // [false]
        range.contains(4)
                .subscribe(System.out::println);    // [true]
    }

    /**
     * compose => 自定义操作
     */
    @Test
    public void compose() {
        Observable.just("Apple", "Orange", "Appla", "Eatla", "HOHO", "Meta")
                .collect(ImmutableList::builder, ImmutableList.Builder::add)
                .map(ImmutableList.Builder::build)
                .subscribe(System.out::println);
        Observable.range(1, 15)
                .collect(ImmutableList::builder, ImmutableList.Builder::add)
                .map(ImmutableList.Builder::build)
                .subscribe(System.out::println);

        System.out.println("---------------------------");

        Observable.just("Apple", "Orange", "Appla", "Eatla", "HOHO", "Meta")
                .compose(toImmutableList())
                .subscribe(System.out::println);
        Observable.range(1, 15)
                .compose(toImmutableList())
                .subscribe(System.out::println);
    }

    public static <T> ObservableTransformer<T, ImmutableList<T>> toImmutableList() {
        return upstream -> upstream.collect(ImmutableList::<T>builder, ImmutableList.Builder::add)
                .map(ImmutableList.Builder::build)
                .toObservable(); // must turn Single into Observable
    }

    private static void log(Object msg) {
        System.out.println(Thread.currentThread().getName() + ": " + msg);
    }

    static void sleep(int timeout, TimeUnit unit) {
        try {
            unit.sleep(timeout);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
