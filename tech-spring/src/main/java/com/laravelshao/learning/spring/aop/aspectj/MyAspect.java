package com.laravelshao.learning.spring.aop.aspectj;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;

/**
 * Created by shaoqinghua on 2017/12/18.
 * Advice类（通知类、增强类） 无需实现任何接口
 */
public class MyAspect {

    //前置通知
    public void before1() {
        System.out.println("前置通知->before1执行.......");
    }

    public void before2() {
        System.out.println("前置通知->before2执行.......");
    }

    /**
     * 前置通知 目标方法运行前增强（可应用于权限控制、记录方法调用日志）
     *
     * @param joinPoint 连接点对象（方法、代理对象、目标对象的包装类型）
     */
    public void before(JoinPoint joinPoint) {
        //获取方法名称
        System.out.println(joinPoint.getSignature().getName());
        //获取目标对象类型
        System.out.println(joinPoint.getTarget().getClass().getName());
        //获取代理对象类型
        System.out.println(joinPoint.getThis().getClass().getName());
        System.out.println("前置通知->before执行.......");
        String loginName = "zhangsan";
        if (joinPoint.getSignature().getName().equals("save")) {
            if ("admin".equals(loginName)) {
                throw new RuntimeException("前置通知->before执行->当前用户：" + loginName + "没有权限执行方法："
                        + joinPoint.getSignature().getName());
            }
        }
    }

    /**
     * 后置通知 目标方法运行前增强（如网上营业厅查询余额后自动下发短信）
     *
     * @param joinPoint   连接点对象（方法、参数、目标对象的包装类型）
     * @param returnValue 目标方法执行后返回值（Object类型）
     */
    public void afterReturing(JoinPoint joinPoint, Object returnValue) {
        System.out.println("后置通知->afterReturing执行->当前话费余额是：" + returnValue);
    }

    /**
     * 环绕通知 在方法执行前后拦截增强（可应用于日志、缓存、权限、性能监控、事务管理）
     * <p>
     * 特点
     * 1.接受参数 ProceedingJoinPoint 可执行的连接点
     * 2.返回值 必须是Object类型
     * 3.必须抛出Throwable异常
     *
     * @param proceedingJoinPoint 可执行的连接点
     * @return
     * @throws Throwable
     */
    public Object around(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        //开启事务
        System.out.println("环绕通知->around执行->事务开启");
        Object proceed = proceedingJoinPoint.proceed();
        System.out.println("环绕通知->around执行->事务提交");
        return proceed;
    }

    /**
     * 抛出通知 在目标方法发生异常情况下进行拦截增强
     *
     * @param joinPoint 连接点
     * @param ex
     */
    public void afterThrowing(JoinPoint joinPoint, Throwable ex) {
        //一旦发生异常 发送邮件或者短信给管理员
        System.out.println("抛出通知->afterThrowing->管理员你好，" + joinPoint.getTarget().getClass().getName() + "的"
                + joinPoint.getSignature().getName() + "方法出现了异常：" + ex.getMessage());
    }

    /**
     * 最终通知 不管方法是否执行 最后都会拦截增强（应用于释放资源、关闭数据库连接、网络连接、释放内存）
     *
     * @param joinPoint
     */
    public void after(JoinPoint joinPoint) {
        System.out.println("最终通知->after->数据库的connection已经关闭了......执行的方法是:" + joinPoint.getSignature().getName());
    }
}
