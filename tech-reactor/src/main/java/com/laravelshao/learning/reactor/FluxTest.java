package com.laravelshao.learning.reactor;

import reactor.core.publisher.Flux;
import reactor.core.scheduler.Scheduler;
import reactor.core.scheduler.Schedulers;

import java.io.IOException;

/**
 * @author qinghua.shao
 * @date 2020/1/2
 * @since 1.0.0
 */
public class FluxTest {

    public static void main(String[] args) throws IOException {

        // 创建序列方式1
        //Flux<String> seq1 = Flux.just("foo", "bar", "foobar");

        // 创建序列方式2
        //List<String> list = Arrays.asList("foo", "bar", "foobar");
        //Flux<String> seq2 = Flux.fromIterable(list);

        // 简单订阅
        //Flux<Integer> ints = Flux.range(1, 3);
        //ints.subscribe(i -> System.out.println(i));

        // 包含异常处理
        //Flux<Integer> ints = Flux.range(1, 4)
        //        .map(i -> {
        //            if (i <= 3) {
        //                return i;
        //            }
        //            throw new RuntimeException("Got to 4");
        //        });
        //
        //// 验证异常处理参数
        //ints.subscribe(i -> System.out.println(i), error -> System.out.println("Error: " + error));

        // 包含异常处理、完成处理
        //Flux<Integer> ints = Flux.range(1, 4);
        //ints.subscribe(i -> System.out.println(i),
        //        error -> System.out.println("Error: " + error),
        //        () -> System.out.println("Done"));

        // 包含异常处理、完成处理、订阅处理
        //Flux<Integer> ints = Flux.range(1, 12);
        //ints.subscribe(i -> System.out.println(i),
        //        error -> System.err.println("Error " + error),
        //        () -> System.out.println("Done"),
        //        sub -> sub.request(10)); // 只订阅10个

        //SampleSubscriber<Integer> ss = new SampleSubscriber<>();
        //Flux<Integer> ints = Flux.range(1, 4);
        //ints.subscribe(i -> System.out.println(i),
        //        error -> System.err.println("Error " + error),
        //        () -> {
        //            System.out.println("Done");
        //        },
        //        s -> s.request(10));
        //ints.subscribe(ss);

        //Flux.range(1, 10)
        //        .doOnRequest(r -> System.out.println("request of " + r))
        //        .subscribe(new BaseSubscriber<Integer>() {
        //
        //            @Override
        //            public void hookOnSubscribe(Subscription subscription) {
        //                request(1);
        //            }
        //
        //            @Override
        //            public void hookOnNext(Integer integer) {
        //                System.out.println("Cancelling after having received " + integer);
        //                cancel();
        //            }
        //        });

        // Flux#generate
        //Flux<String> flux = Flux.generate(
        //        () -> 0,
        //        (state, sink) -> {
        //            sink.next("3 x " + state + " = " + 3 * state);
        //            if (state == 10) {
        //                sink.complete();
        //            }
        //            return state + 1;
        //        });
        //flux.subscribe(str -> System.out.println(str));

        //Flux<String> flux = Flux.generate(
        //        AtomicLong::new,
        //        (state, sink) -> {
        //            long i = state.getAndIncrement();
        //            sink.next("3 x " + i + " = " + 3 * i);
        //            if (i == 10) {
        //                sink.complete();
        //            }
        //            return state;
        //        });
        //flux.subscribe(str -> System.out.println(str));

        //Flux<String> flux = Flux.generate(
        //        AtomicLong::new,
        //        (state, sink) -> {
        //            long i = state.getAndIncrement();
        //            sink.next("3 x " + i + " = " + 3 * i);
        //            if (i == 10) {
        //                sink.complete();
        //            }
        //            return state;
        //        }, (state) -> System.out.println("state: " + state));
        //flux.subscribe(str -> System.out.println(str));

        // Flux#create
        //Flux<String> bridge = Flux.create(sink -> {
        //    myEventProcessor.register(
        //            new MyEventListener<String>() {
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
        //            });
        //});

        // Flux#push
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

        // Flux#handle
        //Flux<String> alphabet = Flux.just(-1, 30, 13, 9, 20)
        //        .handle((i, sink) -> {
        //            String letter = alphabet(i);
        //            if (letter != null) {
        //                sink.next(letter);
        //            }
        //        });
        //alphabet.subscribe(System.out::println);

        // Flux#publishOn
        //Scheduler s = Schedulers.newParallel("parallel-scheduler", 4);
        //final Flux<String> flux = Flux
        //        .range(1, 2)
        //        .map(i -> 10 + i)
        //        .publishOn(s)
        //        .map(i -> "value " + i);
        //new Thread(() -> flux.subscribe(System.out::println));

        // Flux#subscribeOn
        //Scheduler s = Schedulers.newParallel("parallel-scheduler", 4);
        //final Flux<String> flux = Flux
        //        .range(1, 2)
        //        .map(i -> 10 + i)
        //        .subscribeOn(s)
        //        .map(i -> "value " + i);
        //
        //new Thread(() -> flux.subscribe(System.out::println));

    }

    public static String alphabet(int letterNumber) {
        if (letterNumber < 1 || letterNumber > 26) {
            return null;
        }
        int letterIndexAscii = 'A' + letterNumber - 1;
        return "" + (char) letterIndexAscii;
    }
}
