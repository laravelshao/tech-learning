package com.laravelshao.learning.spring.aop.old;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.apache.log4j.Logger;

/**
 * Created by shaoqinghua on 2017/12/18.
 * 传统aop的advice通知类，必须实现MethodInterceptor接口
 */
public class TimeLogInterceptor implements MethodInterceptor {
    private static Logger logger = Logger.getLogger(TimeLogInterceptor.class);

    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        /**
         * 业务 记录目标方法的运行时间
         */
        //调用前记录
        long beforeTime = System.currentTimeMillis();
        //继续执行原方法
        Object proceed = invocation.proceed();
        //调用后记录
        long afterTime = System.currentTimeMillis();
        //总运行时间
        long runTime = afterTime - beforeTime;

        // 记录日志
        logger.info("方法：" + invocation.getMethod().getName() + "的总运行时间为:" + runTime + "毫秒");
        return proceed;
    }
}
