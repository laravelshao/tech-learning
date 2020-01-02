package com.laravelshao.learning.reactor;

import reactor.core.Disposable;
import reactor.core.publisher.Flux;
import reactor.core.publisher.SignalType;

import java.time.Duration;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.LongAdder;

/**
 * 异常处理测试
 *
 * @author qinghua.shao
 * @date 2020/1/2
 * @since 1.0.0
 */
public class ErrorHandleTest {

    public static void main(String[] args) throws InterruptedException {

        // Flux#onErrorReturn
        //Flux.just(1, 2, 0)
        //        .map(i -> "100 / " + i + " = " + (100 / i)) //this triggers an error with 0
        //        .onErrorReturn("Divided by zero :(") // error handling example
        //        .subscribe(i -> System.out.println(i));

        // Flux#onErrorResume
        //Flux.just("key1", "key2")
        //        .flatMap(k -> callExternalService(k)
        //                .onErrorResume(e -> getFromCache(k))
        //        );

        //Flux.just("timeout1", "unknown", "key2")
        //        .flatMap(k -> callExternalService(k)
        //                .onErrorResume(error -> {
        //                    if (error instanceof TimeoutException)
        //                        return getFromCache(k);
        //                    else if (error instanceof UnknownKeyException)
        //                        return registerNewEntry(k, "DEFAULT");
        //                    else
        //                        return Flux.error(error);
        //                })
        //        );

        //erroringFlux.onErrorResume(error -> Mono.just(
        //        MyWrapper.fromError(error)
        //));

        //Flux.just("timeout1")
        //        .flatMap(k -> callExternalService(k))
        //        .onErrorResume(original -> Flux.error(
        //                new BusinessException("oops, SLA exceeded", original))
        //        );

        // 等同于
        //Flux.just("timeout1")
        //        .flatMap(k -> callExternalService(k))
        //        .onErrorMap(original -> new BusinessException("oops, SLA exceeded", original));

        //LongAdder failureStat = new LongAdder();
        //Flux<String> flux =
        //        Flux.just("unknown")
        //                .flatMap(k -> callExternalService(k)
        //                        .doOnError(e -> {
        //                            failureStat.increment();
        //                            log("uh oh, falling back, service failed for key " + k);
        //                        })
        //
        //                );

        //Stats stats = new Stats();
        //LongAdder statsCancel = new LongAdder();
        //
        //Flux<String> flux =
        //        Flux.just("foo", "bar")
        //                .doOnSubscribe(s -> stats.startTimer())
        //                .doFinally(type -> {
        //                    stats.stopTimerAndRecordTiming();
        //                    if (type == SignalType.CANCEL)
        //                        statsCancel.increment();
        //                })
        //                .take(1);

        //AtomicBoolean isDisposed = new AtomicBoolean();
        //Disposable disposableInstance = new Disposable() {
        //    @Override
        //    public void dispose() {
        //        isDisposed.set(true);
        //    }
        //
        //    @Override
        //    public String toString() {
        //        return "DISPOSABLE";
        //    }
        //};
        //Flux<String> flux =
        //        Flux.using(
        //                () -> disposableInstance,
        //                disposable -> Flux.just(disposable.toString()),
        //                Disposable::dispose
        //        );

        //Flux<String> flux =
        //        Flux.interval(Duration.ofMillis(250))
        //                .map(input -> {
        //                    if (input < 3) {
        //                        return "tick " + input;
        //                    }
        //                    throw new RuntimeException("boom");
        //                })
        //                .onErrorReturn("Uh oh");
        //flux.subscribe(System.out::println);
        //Thread.sleep(2100);

        //Flux.interval(Duration.ofMillis(250))
        //        .map(input -> {
        //            if (input < 3) {
        //                return "tick " + input;
        //            }
        //            throw new RuntimeException("boom");
        //        })
        //        .retry(1)
        //        .elapsed()
        //        .subscribe(System.out::println, System.err::println);
        //Thread.sleep(2100);

    }
}
