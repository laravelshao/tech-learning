package com.laravelshao.learning.reactor;

import org.junit.Test;
import org.reactivestreams.Subscription;
import reactor.core.publisher.BaseSubscriber;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Scheduler;
import reactor.core.scheduler.Schedulers;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Flux 测试
 *
 * @author qinghua.shao
 * @date 2020/2/9
 * @since 1.0.0
 */
public class FluxTest {

    /**
     * just => 可以指定序列中包含的全部元素，创建出来的Flux源序列在发布这些元素后会自动结束
     */
    @Test
    public void just() {
        Flux<String> seq1 = Flux.just("foo", "bar", "foobar");
        seq1.subscribe(i -> System.out.println(i));
    }

    /**
     * fromArray、fromIterable和fromStream => 可以从数组、Iterable对象或Stream对象中创建Flux对象
     */
    @Test
    public void fromXXX() {
        List<String> list = Arrays.asList("foo", "bar", "foobar");
        Flux<String> seq2 = Flux.fromIterable(list);
        seq2.subscribe(i -> System.out.println(i));
    }

    /**
     * range => 创建包含从 start 起始的 count 个数量的 Integer 类型的源序列
     */
    @Test
    public void range() {
        Flux<Integer> ints = Flux.range(1, 3);
        ints.subscribe(i -> System.out.println(i));
    }

    /**
     * 包含异常处理
     */
    @Test
    public void error() {
        Flux<Integer> ints = Flux.range(1, 4)
                .map(i -> {
                    if (i <= 3) {
                        return i;
                    }
                    throw new RuntimeException("Got to 4");
                });

        // 验证异常处理参数
        ints.subscribe(i -> System.out.println(i), error -> System.out.println("Error: " + error));
    }

    /**
     * 包含异常处理、完成处理
     */
    @Test
    public void errorFinish() {
        Flux<Integer> ints = Flux.range(1, 4);
        ints.subscribe(i -> System.out.println(i),
                error -> System.out.println("Error: " + error),
                () -> System.out.println("Done"));
    }

    /**
     * 包含异常处理、完成处理、订阅处理
     */
    @Test
    public void errorFinishSubscription() {
        Flux<Integer> ints = Flux.range(1, 12);
        ints.subscribe(i -> System.out.println(i),
                error -> System.err.println("Error " + error),
                () -> System.out.println("Done"),
                sub -> sub.request(10)); // 只订阅10个
    }

    /**
     * baseSubscriber 测试
     */
    @Test
    public void baseSubscriber() {
        SampleSubscriber<Integer> ss = new SampleSubscriber<>();
        Flux<Integer> ints = Flux.range(1, 4);
        ints.subscribe(i -> System.out.println(i),
                error -> System.err.println("Error " + error),
                () -> System.out.println("Done"),
                sub -> sub.request(10));
        ints.subscribe(ss);

        System.out.println("==============================================");

        Flux.range(1, 10)
                .doOnRequest(r -> System.out.println("request of " + r))
                .subscribe(new BaseSubscriber<Integer>() {

                    @Override
                    public void hookOnSubscribe(Subscription subscription) {
                        request(1);
                    }

                    @Override
                    public void hookOnNext(Integer integer) {
                        System.out.println("Cancelling after having received " + integer);
                        cancel();
                    }
                });
    }

    /**
     * generate
     */
    @Test
    public void generate() {
        Flux<String> flux = Flux.generate(
                () -> 0,
                (state, sink) -> {
                    sink.next("3 x " + state + " = " + 3 * state);
                    if (state == 10) {
                        sink.complete();
                    }
                    return state + 1;
                });
        flux.subscribe(str -> System.out.println(str));

        System.out.println("==============================================");

        Flux<String> flux2 = Flux.generate(
                AtomicLong::new,
                (state, sink) -> {
                    long i = state.getAndIncrement();
                    sink.next("3 x " + i + " = " + 3 * i);
                    if (i == 10) {
                        sink.complete();
                    }
                    return state;
                });
        flux2.subscribe(str -> System.out.println(str));

        System.out.println("==============================================");

        Flux<String> flux3 = Flux.generate(
                AtomicLong::new,
                (state, sink) -> {
                    long i = state.getAndIncrement();
                    sink.next("3 x " + i + " = " + 3 * i);
                    if (i == 10) {
                        sink.complete();
                    }
                    return state;
                }, (state) -> System.out.println("state: " + state));
        flux3.subscribe(str -> System.out.println(str));
    }

    /**
     * create
     */
    @Test
    public void create() {
        //Flux<String> bridge = Flux.create(sink -> {
        //    myEventProcessor.register(
        //            new MyEventListener<String>() {
        //
        //                public void onDataChunk(List<String> chunk) {
        //                    for (String s : chunk) {
        //                        sink.next(s);
        //                    }
        //                }
        //
        //                public void processComplete() {
        //                    sink.complete();
        //                }
        //            });
        //});
    }

    /**
     * push
     */
    @Test
    public void push() {
        //Flux<String> bridge = Flux.push(sink -> {
        //    myEventProcessor.register(
        //            new SingleThreadEventListener<String>() {
        //
        //                public void onDataChunk(List<String> chunk) {
        //                    for(String s : chunk) {
        //                        sink.next(s);
        //                    }
        //                }
        //
        //                public void processComplete() {
        //                    sink.complete();
        //                }
        //
        //                public void processError(Throwable e) {
        //                    sink.error(e);
        //                }
        //            });
        //});
    }

    /**
     * handle
     */
    @Test
    public void handle() {
        Flux<String> alphabet = Flux.just(-1, 30, 13, 9, 20)
                .handle((i, sink) -> {
                    String letter = alphabet(i);
                    if (letter != null) {
                        sink.next(letter);
                    }
                });
        alphabet.subscribe(System.out::println);
    }

    /**
     * publishOn
     */
    @Test
    public void publishOn() {
        Scheduler s = Schedulers.newParallel("parallel-scheduler", 4);
        final Flux<String> flux = Flux.range(1, 2)
                .map(i -> 10 + i)
                .publishOn(s)
                .map(i -> "value " + i);
        new Thread(() -> flux.subscribe(System.out::println));
    }

    /**
     * subscribeOn
     */
    @Test
    public void subscribeOn() {
        Scheduler s = Schedulers.newParallel("parallel-scheduler", 4);
        final Flux<String> flux = Flux.range(1, 2)
                .map(i -> 10 + i)
                .subscribeOn(s)
                .map(i -> "value " + i);

        new Thread(() -> flux.subscribe(System.out::println));
    }

    public static String alphabet(int letterNumber) {
        if (letterNumber < 1 || letterNumber > 26) {
            return null;
        }
        int letterIndexAscii = 'A' + letterNumber - 1;
        return "" + (char) letterIndexAscii;
    }

}
