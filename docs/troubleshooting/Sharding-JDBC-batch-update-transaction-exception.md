## 异常问题

最近线上环境有一个服务频繁出现更新异常，提示不能提交JDBC事务，但是事务并没有回滚。

> org.springframework.transaction.TransactionSystemException：Could not commit JDBC transaction; nested exception is java.sql.SQLException

完整报错信息：

```
com.XXX.XXXX.XX.XXXXXX.facade.RuleUpdateFacadeService.updatePromotionRule throw Exception! globalTicket= org.springframework.transaction.TransactionSystemException: Could not commit JDBC transaction; nested exception is java.sql.SQLException
at org.springframework.jdbc.datasource.DataSourceTransactionManager.doCommit(DataSourceTransactionManager.java:332)
at org.springframework.transaction.support.AbstractPlatformTransactionManager.processCommit(AbstractPlatformTransactionManager.java:746)
at org.springframework.transaction.support.AbstractPlatformTransactionManager.commit(AbstractPlatformTransactionManager.java:714)
at org.springframework.transaction.interceptor.TransactionAspectSupport.commitTransactionAfterReturning(TransactionAspectSupport.java:533)
at org.springframework.transaction.interceptor.TransactionAspectSupport.invokeWithinTransaction(TransactionAspectSupport.java:304)
at org.springframework.transaction.interceptor.TransactionInterceptor.invoke(TransactionInterceptor.java:98)
at org.springframework.aop.framework.ReflectiveMethodInvocation.proceed(ReflectiveMethodInvocation.java:186)
at org.springframework.aop.framework.CglibAopProxy$DynamicAdvisedInterceptor.intercept(CglibAopProxy.java:688)
at com.XXX.XXXX.XX.XXXXXX.strategy.RuleUpdateStrategy4Redemption$$EnhancerBySpringCGLIB$$acd37971.update(<generated>)
at com.XXX.XXXX.XX.XXXXXX.facade.RuleUpdateFacadeService.updatePromotionRule(RuleUpdateFacadeService.java:71)
at com.XXX.XXXX.XX.XXXXXX.facade.RuleUpdateFacadeService$$FastClassBySpringCGLIB$$1e0d036d.invoke(<generated>)
at org.springframework.cglib.proxy.MethodProxy.invoke(MethodProxy.java:218)
at org.springframework.aop.framework.CglibAopProxy$CglibMethodInvocation.invokeJoinpoint(CglibAopProxy.java:749)
at org.springframework.aop.framework.ReflectiveMethodInvocation.proceed(ReflectiveMethodInvocation.java:163)
at org.springframework.aop.aspectj.MethodInvocationProceedingJoinPoint.proceed(MethodInvocationProceedingJoinPoint.java:88)
at com.XXX.XXXX.XX.troop.exception.aop.ValidFacadeAspect.aroundAdvice(ValidFacadeAspect.java:77)
at sun.reflect.GeneratedMethodAccessor133.invoke(Unknown Source)
at sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
at java.lang.reflect.Method.invoke(Method.java:498)
at org.springframework.aop.aspectj.AbstractAspectJAdvice.invokeAdviceMethodWithGivenArgs(AbstractAspectJAdvice.java:644)
at org.springframework.aop.aspectj.AbstractAspectJAdvice.invokeAdviceMethod(AbstractAspectJAdvice.java:633)
at org.springframework.aop.aspectj.AspectJAroundAdvice.invoke(AspectJAroundAdvice.java:70)
at org.springframework.aop.framework.ReflectiveMethodInvocation.proceed(ReflectiveMethodInvocation.java:186)
at org.springframework.aop.aspectj.AspectJAfterThrowingAdvice.invoke(AspectJAfterThrowingAdvice.java:62)
at org.springframework.aop.framework.ReflectiveMethodInvocation.proceed(ReflectiveMethodInvocation.java:186)
at org.springframework.aop.interceptor.ExposeInvocationInterceptor.invoke(ExposeInvocationInterceptor.java:93)
at org.springframework.aop.framework.ReflectiveMethodInvocation.proceed(ReflectiveMethodInvocation.java:186)
at org.springframework.aop.framework.CglibAopProxy$DynamicAdvisedInterceptor.intercept(CglibAopProxy.java:688)
at com.XXX.XXXX.XX.XXXXXX.facade.RuleUpdateFacadeService$$EnhancerBySpringCGLIB$$e135e9f3.updatePromotionRule(<generated>)
at com.alibaba.dubbo.common.bytecode.Wrapper0.invokeMethod(Wrapper0.java)
at com.alibaba.dubbo.rpc.proxy.javassist.JavassistProxyFactory$1.doInvoke(JavassistProxyFactory.java:45)
at com.alibaba.dubbo.rpc.proxy.AbstractProxyInvoker.invoke(AbstractProxyInvoker.java:71)
at com.alibaba.dubbo.config.invoker.DelegateProviderMetaDataInvoker.invoke(DelegateProviderMetaDataInvoker.java:48)
at com.alibaba.dubbo.rpc.protocol.InvokerWrapper.invoke(InvokerWrapper.java:52)
at com.XXX.XXXX.XX.troop.common.log.filters.AttachValidationFilter.invoke(AttachValidationFilter.java:34)
at com.alibaba.dubbo.rpc.protocol.ProtocolFilterWrapper$1.invoke(ProtocolFilterWrapper.java:68)
at com.XXX.cat.dubbo.support.CatTransactionFilter.invoke(CatTransactionFilter.java:140)
at com.alibaba.dubbo.rpc.protocol.ProtocolFilterWrapper$1.invoke(ProtocolFilterWrapper.java:68)
at com.alibaba.dubbo.rpc.filter.ExceptionFilter.invoke(ExceptionFilter.java:61)
at com.alibaba.dubbo.rpc.protocol.ProtocolFilterWrapper$1.invoke(ProtocolFilterWrapper.java:68)
at com.XXX.dubbo.monitor.support.XXXMonitorFilter.invoke(XXXMonitorFilter.java:82)
at com.alibaba.dubbo.rpc.protocol.ProtocolFilterWrapper$1.invoke(ProtocolFilterWrapper.java:68)
at com.alibaba.dubbo.rpc.filter.TimeoutFilter.invoke(TimeoutFilter.java:41)
at com.alibaba.dubbo.rpc.protocol.ProtocolFilterWrapper$1.invoke(ProtocolFilterWrapper.java:68)
at com.alibaba.dubbo.rpc.protocol.dubbo.filter.TraceFilter.invoke(TraceFilter.java:77)
at com.alibaba.dubbo.rpc.protocol.ProtocolFilterWrapper$1.invoke(ProtocolFilterWrapper.java:68)
at com.XXX.dubbo.trace.TraceContextFilter.invoke(TraceContextFilter.java:45)
at com.alibaba.dubbo.rpc.protocol.ProtocolFilterWrapper$1.invoke(ProtocolFilterWrapper.java:68)
at com.XXX.cat.dubbo.support.CatRemoteCallFilter.invoke(CatRemoteCallFilter.java:20)
at com.alibaba.dubbo.rpc.protocol.ProtocolFilterWrapper$1.invoke(ProtocolFilterWrapper.java:68)
at com.alibaba.dubbo.rpc.filter.ContextFilter.invoke(ContextFilter.java:72)
at com.alibaba.dubbo.rpc.protocol.ProtocolFilterWrapper$1.invoke(ProtocolFilterWrapper.java:68)
at com.alibaba.dubbo.rpc.filter.GenericFilter.invoke(GenericFilter.java:131)
at com.alibaba.dubbo.rpc.protocol.ProtocolFilterWrapper$1.invoke(ProtocolFilterWrapper.java:68)
at com.alibaba.dubbo.rpc.filter.ClassLoaderFilter.invoke(ClassLoaderFilter.java:37)
at com.alibaba.dubbo.rpc.protocol.ProtocolFilterWrapper$1.invoke(ProtocolFilterWrapper.java:68)
at com.alibaba.dubbo.rpc.filter.EchoFilter.invoke(EchoFilter.java:37)
at com.alibaba.dubbo.rpc.protocol.ProtocolFilterWrapper$1.invoke(ProtocolFilterWrapper.java:68)
at com.alibaba.dubbo.rpc.protocol.dubbo.DubboProtocol$1.reply(DubboProtocol.java:98)
at com.alibaba.dubbo.remoting.exchange.support.header.HeaderExchangeHandler.handleRequest(HeaderExchangeHandler.java:96)
at com.alibaba.dubbo.remoting.exchange.support.header.HeaderExchangeHandler.received(HeaderExchangeHandler.java:168)
at com.alibaba.dubbo.remoting.transport.DecodeHandler.received(DecodeHandler.java:50)
at com.alibaba.dubbo.remoting.transport.dispatcher.ChannelEventRunnable.run(ChannelEventRunnable.java:79)
at java.util.concurrent.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1149)
at java.util.concurrent.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor.java:624)
at java.lang.Thread.run(Thread.java:748)
Caused by: java.sql.SQLException
at io.shardingsphere.core.jdbc.adapter.WrapperAdapter.throwSQLExceptionIfNecessary(WrapperAdapter.java:82)
at io.shardingsphere.core.jdbc.adapter.AbstractConnectionAdapter.commit(AbstractConnectionAdapter.java:101)
at org.springframework.jdbc.datasource.DataSourceTransactionManager.doCommit(DataSourceTransactionManager.java:329)
```

上述报错由 `sharding-jdbc` 抛出，`sharding-jdbc` commit 异常，但是 `mysql` 的事务已经提交成功，查看完整的变更记录，发现线上更新操作存在大量这种场景，而新增场景没有发现一次该异常。

## 排除步骤

1. 将线上存在异常的数据导入至dev开发环境，模拟实际的操作流程，没有发现问题，可正常执行并正确响应。
2. 在更新事务方法中手动添加异常，再次执行更新操作可正常回滚事务。

3. 在CAT上查看异常发生时间点，然后到 `Kibana` 上查看 `k8slog` ，发现存在 `TransactionSystemException` 异常时，k8s上都存在 `jdbc` 的 `CommunicationsException`。怀疑是不是数据源配置有问题，项目中使用 `Druid` 作为数据库连接池。

> com.mysql.jdbc.exceptions.jdbc4.CommunicationsException: The last packet successfully received from the server was 7,501,223 milliseconds ago. The last packet sent successfully to the server was 7,501,224 milliseconds ago. is longer than the server configured value of 'wait_timeout'. You should consider either expiring and/or testing connection validity before use in your application, increasing the server configured values for client timeouts, or using the Connector/J connection property 'autoReconnect=true' to avoid this problem.

4. 检查数据库连接池配置，没有发现异常，和其它服务配置相同，具体检查每个配置的细致说明，也没发现存在问题。

```properties
jdbc.share.dataSourceClassName = com.alibaba.druid.pool.DruidDataSource
jdbc.share.driverClassName = com.mysql.jdbc.Driver
# 配置初始化大小、最小、最大
jdbc.share.initialSize = 5
jdbc.share.minIdle = 5
jdbc.share.maxActive = 50
# 配置获取连接等待超时的时间
jdbc.share.maxWait = 60000
# 配置间隔多久才进行一次检测，检测需要关闭的空闲连接，单位是毫秒
jdbc.share.timeBetweenEvictionRunsMillis = 60000
# 配置一个连接在池中最小生存的时间，单位是毫秒
jdbc.share.minEvictableIdleTimeMillis = 3600000
jdbc.share.validationQuery = SELECT 'x'
# 关闭abanded连接时输出错误日志
jdbc.share.logAbandoned = true
# 超过时间限制是否回收连接
jdbc.share.removeAbandoned = true
# 超时时间 单位为秒 180秒=3分钟
jdbc.share.removeAbandonedTimeout = 180
jdbc.share.testWhileIdle = true
jdbc.share.testOnBorrow = false
jdbc.share.testOnReturn = false
# 打开PSCache，并且指定每个连接上PSCache的大小，mysql设置为false
jdbc.share.poolPreparedStatements = false
jdbc.share.maxPoolPreparedStatementPerConnectionSize = 20
```

5. 偶然了解到 `sharding-jdbc` 在3.0版本之前不支持 `batch insert`，查阅了 `sharding-jdbc` release 中的新功能记录(https://github.com/apache/shardingsphere/releases?after=4.0.0)，在3.0.0.M1版本中支持了批量添加功能 Support batch INSERT(https://github.com/sharding-sphere/sharding-sphere/issues/290)，支持类似的批量插入sql语句。

```sql
INSERT INTO t_table (xx, xx) VALUES
(xx,xx),
(xx,xx),
(xx,xx);
```

6. 因此，怀疑是不是 `sharding-jdbc` 不支持 `batch update` 方式，搜索`sharding-jdbc` issues 列表，搜索到一个类似的issues (https://github.com/apache/shardingsphere/issues/6665)， 如果是利用 `mybatis` `foreach` 批量更新，类似下列语句更新：

```xml
<update id="batchUpdateList" parameterType="java.util.List">
    <foreach collection="list" item="item" separator=";">
        update table
        set name = #{item.name},
        where id = #{item.id}
    </foreach>
</update>
```

最终生成实际的 `sql` 语句，`sharding-jdbc` 明确不支持并且也没有支持打算，因为批量更新的数据有可能会路由至多个分库节点，会导致分布式事务问题。

```sql
Actual SQL: ds_1 ::: update stock_change_detail_023
           set 
           update_time = now()
           where
           id = ?
           and tenant_id = ?
           and warehouse_no = ?
         ;
           update stock_change_detail
           set 
           update_time = now()
           where 
           id = ?
           and tenant_id = ?
           and warehouse_no = ? ::: [[[1, jd, 26, 2, jd, 26]]]
```

而项目中也存在一个通过 `foreach` 批量更新的方法。

7. 因为该操作是B端后台操作，性能要求不高，将批量更新语句修改成循环单条执行。

## 参考资料

- Sharding-JDBC version 3.0.0.M1 releases features：support batch INSERT (https://github.com/apache/shardingsphere/releases?after=4.0.0)
- Sharding-JDBC version 3.0.0.M1 support batch INSERT issues(https://github.com/apache/shardingsphere/issues/290)
- Sharding jdbc do not support batch update(https://github.com/apache/shardingsphere/issues/6665)