package com.laravelshao.learning.concurrent.threadlocal;

import com.alibaba.ttl.TransmittableThreadLocal;
import com.alibaba.ttl.TtlRunnable;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * TransmittableThreadLocal 传递测试
 *
 * @author qinghua.shao
 * @date 2025/3/28
 * @since 1.0.0
 */
public class TransmittableThreadLocalTest {

    private static final ExecutorService EXECUTOR = Executors.newSingleThreadExecutor();

    private static final TransmittableThreadLocal<String> INHERITABLE_THREAD_LOCAL = new TransmittableThreadLocal<String>() {
        @Override
        protected String initialValue() {
            return "init value";
        }
    };

    public static void main(String[] args) {
        final Runnable printCtxTask = () -> System.out.println(INHERITABLE_THREAD_LOCAL.get());

        // 在主线程设置了上下文
        INHERITABLE_THREAD_LOCAL.set("first value");

        // 向线程池提交任务，打印上下文内容，期望输出：first value, 实际输出：first value
        EXECUTOR.submit(TtlRunnable.get(printCtxTask));

        // 移除线程原始上下文内容
        INHERITABLE_THREAD_LOCAL.set("second value");

        // 向线程池提交任务，打印上下文内容，期望输出：second value, 实际输出：second value
        EXECUTOR.submit(TtlRunnable.get(printCtxTask));

        EXECUTOR.shutdown();
    }
}
