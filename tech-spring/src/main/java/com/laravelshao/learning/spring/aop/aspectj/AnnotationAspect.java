package com.laravelshao.learning.spring.aop.aspectj;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

/**
 * Created by shaoqinghua on 2018/8/19.
 */
@Component
//@Aspect // 声明切面
public class AnnotationAspect {

    /**
     * 配置切入点 该方法无方法体 主要为方便同类中其他方法使用此处配置切入点
     * 切点的集合，这个表达式所描述的是一个虚拟面（规则）
     */
    @Pointcut("bean(*Service)")
    public void annotationPointcut() {
    }

    /**
     * 前置通知 目标方法运行前增强（可应用于权限控制、记录方法调用日志）
     *
     * @param joinPoint 连接点对象（方法、代理对象、目标对象的包装类型）
     */
    @Before("annotationPointcut()")
    public void before(JoinPoint joinPoint) {
        //获取方法名称
        System.out.println(joinPoint.getSignature().getName());
        //获取目标对象类型
        System.out.println(joinPoint.getTarget().getClass().getName());
        //获取代理对象类型
        System.out.println(joinPoint.getThis().getClass().getName());
        System.out.println("前置通知(Annotation)->before执行.......");
        String loginName = "zhangsan";
        if (joinPoint.getSignature().getName().equals("save")) {
            if ("admin".equals(loginName)) {
                throw new RuntimeException("前置通知(Annotation)->before执行->当前用户：" + loginName + "没有权限执行方法："
                        + joinPoint.getSignature().getName());
            }
        }
    }

    /**
     * 后置通知 目标方法运行前增强（如网上营业厅查询余额后自动下发短信）
     *
     * @param joinPoint 连接点对象（方法、参数、目标对象的包装类型）
     */
    @AfterReturning("annotationPointcut()")
    public void afterReturing(JoinPoint joinPoint) {
        System.out.println("后置通知(Annotation)->afterReturing执行->当前话费余额是：100");
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
    @Around("annotationPointcut()")
    public Object around(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        //开启事务
        System.out.println("环绕通知(Annotation)->around执行->事务开启");
        Object proceed = proceedingJoinPoint.proceed();
        System.out.println("环绕通知(Annotation)->around执行->事务提交");
        return proceed;
    }

    /**
     * 抛出通知 在目标方法发生异常情况下进行拦截增强
     *
     * @param joinPoint 连接点
     * @param ex
     */
    @AfterThrowing(pointcut = "annotationPointcut()", throwing = "ex")
    public void afterThrowing(JoinPoint joinPoint, Throwable ex) {
        //一旦发生异常 发送邮件或者短信给管理员
        System.out.println("抛出通知(Annotation)->afterThrowing->管理员你好，" + joinPoint.getTarget().getClass().getName() + "的"
                + joinPoint.getSignature().getName() + "方法出现了异常：" + ex.getMessage());
    }
    
    /**
     * 最终通知 不管方法是否执行 最后都会拦截增强（应用于释放资源、关闭数据库连接、网络连接、释放内存）
     *
     * @param joinPoint
     */
    @After("annotationPointcut()")
    public void after(JoinPoint joinPoint) {
        System.out.println("最终通知(Annotation)->after->数据库的connection已经关闭了......执行的方法是:" + joinPoint.getSignature().getName());
    }
}
