# Elastic Job 异常处理



1. elastic-job 整合 Spring 异常处理


```java
十月 03, 2019 12:54:19 下午 org.apache.catalina.core.StandardContext listenerStart
严重: Exception sending context initialized event to listener instance of class org.springframework.web.context.ContextLoaderListener
org.springframework.beans.factory.BeanCreationException: Error creating bean with name 'zkCenter': Invocation of init method failed; nested exception is java.lang.NoClassDefFoundError: org/apache/curator/connection/ConnectionHandlingPolicy
	at org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.initializeBean(AbstractAutowireCapableBeanFactory.java:1699)
	at org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.doCreateBean(AbstractAutowireCapableBeanFactory.java:573)
	at org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.createBean(AbstractAutowireCapableBeanFactory.java:495)
	at org.springframework.beans.factory.support.AbstractBeanFactory.lambda$doGetBean$0(AbstractBeanFactory.java:317)
	at org.springframework.beans.factory.support.DefaultSingletonBeanRegistry.getSingleton(DefaultSingletonBeanRegistry.java:222)
	at org.springframework.beans.factory.support.AbstractBeanFactory.doGetBean(AbstractBeanFactory.java:315)
	at org.springframework.beans.factory.support.AbstractBeanFactory.getBean(AbstractBeanFactory.java:199)
	at org.springframework.beans.factory.support.DefaultListableBeanFactory.preInstantiateSingletons(DefaultListableBeanFactory.java:759)
	at org.springframework.context.support.AbstractApplicationContext.finishBeanFactoryInitialization(AbstractApplicationContext.java:869)
	at org.springframework.context.support.AbstractApplicationContext.refresh(AbstractApplicationContext.java:550)
	at org.springframework.web.context.ContextLoader.configureAndRefreshWebApplicationContext(ContextLoader.java:409)
	at org.springframework.web.context.ContextLoader.initWebApplicationContext(ContextLoader.java:291)
	at org.springframework.web.context.ContextLoaderListener.contextInitialized(ContextLoaderListener.java:103)
	at org.apache.catalina.core.StandardContext.listenerStart(StandardContext.java:4939)
	at org.apache.catalina.core.StandardContext.startInternal(StandardContext.java:5434)
	at org.apache.catalina.util.LifecycleBase.start(LifecycleBase.java:150)
	at org.apache.catalina.core.ContainerBase$StartChild.call(ContainerBase.java:1559)
	at org.apache.catalina.core.ContainerBase$StartChild.call(ContainerBase.java:1549)
	at java.util.concurrent.FutureTask.run$$$capture(FutureTask.java:266)
	at java.util.concurrent.FutureTask.run(FutureTask.java)
	at java.util.concurrent.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1149)
	at java.util.concurrent.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor.java:624)
	at java.lang.Thread.run(Thread.java:748)
Caused by: java.lang.NoClassDefFoundError: org/apache/curator/connection/ConnectionHandlingPolicy
	at org.apache.curator.framework.CuratorFrameworkFactory.builder(CuratorFrameworkFactory.java:78)
	at com.dangdang.ddframe.job.reg.zookeeper.ZookeeperRegistryCenter.init(ZookeeperRegistryCenter.java:72)
	at sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
	at sun.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:62)
	at sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
	at java.lang.reflect.Method.invoke(Method.java:498)
	at org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.invokeCustomInitMethod(AbstractAutowireCapableBeanFactory.java:1824)
	at org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.invokeInitMethods(AbstractAutowireCapableBeanFactory.java:1767)
	at org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.initializeBean(AbstractAutowireCapableBeanFactory.java:1695)
	... 22 more
Caused by: java.lang.ClassNotFoundException: org.apache.curator.connection.ConnectionHandlingPolicy
	at org.apache.catalina.loader.WebappClassLoader.loadClass(WebappClassLoader.java:1702)
	at org.apache.catalina.loader.WebappClassLoader.loadClass(WebappClassLoader.java:1547)
	... 31 more
```


```java
严重: Exception sending context initialized event to listener instance of class org.springframework.web.context.ContextLoaderListener
org.springframework.beans.factory.BeanCreationException: Error creating bean with name 'com.dangdang.ddframe.job.lite.spring.api.SpringJobScheduler#0': Invocation of init method failed; nested exception is java.lang.NoSuchMethodError: org.apache.curator.framework.api.CreateBuilder.creatingParentsIfNeeded()Lorg/apache/curator/framework/api/ProtectACLCreateModePathAndBytesable;
	at org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.initializeBean(AbstractAutowireCapableBeanFactory.java:1699)
	at org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.doCreateBean(AbstractAutowireCapableBeanFactory.java:573)
	at org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.createBean(AbstractAutowireCapableBeanFactory.java:495)
	at org.springframework.beans.factory.support.AbstractBeanFactory.lambda$doGetBean$0(AbstractBeanFactory.java:317)
	at org.springframework.beans.factory.support.DefaultSingletonBeanRegistry.getSingleton(DefaultSingletonBeanRegistry.java:222)
	at org.springframework.beans.factory.support.AbstractBeanFactory.doGetBean(AbstractBeanFactory.java:315)
	at org.springframework.beans.factory.support.AbstractBeanFactory.getBean(AbstractBeanFactory.java:199)
	at org.springframework.beans.factory.support.DefaultListableBeanFactory.preInstantiateSingletons(DefaultListableBeanFactory.java:759)
	at org.springframework.context.support.AbstractApplicationContext.finishBeanFactoryInitialization(AbstractApplicationContext.java:869)
	at org.springframework.context.support.AbstractApplicationContext.refresh(AbstractApplicationContext.java:550)
	at org.springframework.web.context.ContextLoader.configureAndRefreshWebApplicationContext(ContextLoader.java:409)
	at org.springframework.web.context.ContextLoader.initWebApplicationContext(ContextLoader.java:291)
	at org.springframework.web.context.ContextLoaderListener.contextInitialized(ContextLoaderListener.java:103)
	at org.apache.catalina.core.StandardContext.listenerStart(StandardContext.java:4939)
	at org.apache.catalina.core.StandardContext.startInternal(StandardContext.java:5434)
	at org.apache.catalina.util.LifecycleBase.start(LifecycleBase.java:150)
	at org.apache.catalina.core.ContainerBase$StartChild.call(ContainerBase.java:1559)
	at org.apache.catalina.core.ContainerBase$StartChild.call(ContainerBase.java:1549)
	at java.util.concurrent.FutureTask.run$$$capture(FutureTask.java:266)
	at java.util.concurrent.FutureTask.run(FutureTask.java)
	at java.util.concurrent.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1149)
	at java.util.concurrent.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor.java:624)
	at java.lang.Thread.run(Thread.java:748)
Caused by: java.lang.NoSuchMethodError: org.apache.curator.framework.api.CreateBuilder.creatingParentsIfNeeded()Lorg/apache/curator/framework/api/ProtectACLCreateModePathAndBytesable;
	at com.dangdang.ddframe.job.reg.zookeeper.ZookeeperRegistryCenter.persist(ZookeeperRegistryCenter.java:218)
	at com.dangdang.ddframe.job.lite.internal.storage.JobNodeStorage.replaceJobNode(JobNodeStorage.java:160)
	at com.dangdang.ddframe.job.lite.internal.config.ConfigurationService.persist(ConfigurationService.java:72)
	at com.dangdang.ddframe.job.lite.internal.schedule.SchedulerFacade.updateJobConfiguration(SchedulerFacade.java:103)
	at com.dangdang.ddframe.job.lite.api.JobScheduler.init(JobScheduler.java:105)
	at sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
	at sun.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:62)
	at sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
	at java.lang.reflect.Method.invoke(Method.java:498)
	at org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.invokeCustomInitMethod(AbstractAutowireCapableBeanFactory.java:1824)
	at org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.invokeInitMethods(AbstractAutowireCapableBeanFactory.java:1767)
	at org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.initializeBean(AbstractAutowireCapableBeanFactory.java:1695)
	... 22 more
```

curator-client版本冲突，排除elastic-job-lite-core引入版本为4.0.1的curator-framework及curator-recipes，单独引入curator-framework 2.10.0，curator-recipes 2.10.0。

```xml
<dependencies>
        <!-- 引入elastic-job-lite核心模块 -->
        <dependency>
            <groupId>com.dangdang</groupId>
            <artifactId>elastic-job-lite-core</artifactId>
            <exclusions>
                <exclusion>
                    <artifactId>curator-framework</artifactId>
                    <groupId>org.apache.curator</groupId>
                </exclusion>
                <exclusion>
                    <artifactId>curator-recipes</artifactId>
                    <groupId>org.apache.curator</groupId>
                </exclusion>
            </exclusions>
        </dependency>
        <dependency>
            <groupId>org.apache.curator</groupId>
            <artifactId>curator-framework</artifactId>
            <version>2.10.0</version>
        </dependency>
        <dependency>
            <groupId>org.apache.curator</groupId>
            <artifactId>curator-recipes</artifactId>
            <version>2.10.0</version>
            <exclusions>
                <exclusion>
                    <artifactId>curator-framework</artifactId>
                    <groupId>org.apache.curator</groupId>
                </exclusion>
            </exclusions>
        </dependency>
     
    </dependencies>
```



```java
十月 03, 2019 1:32:19 下午 org.apache.catalina.core.StandardContext listenerStart
严重: Exception sending context initialized event to listener instance of class org.springframework.web.context.ContextLoaderListener
org.springframework.beans.factory.BeanCreationException: Error creating bean with name 'zkCenter': Invocation of init method failed; nested exception is java.lang.NoSuchMethodError: com.google.common.util.concurrent.MoreExecutors.sameThreadExecutor()Lcom/google/common/util/concurrent/ListeningExecutorService;
	at org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.initializeBean(AbstractAutowireCapableBeanFactory.java:1699)
	at org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.doCreateBean(AbstractAutowireCapableBeanFactory.java:573)
	at org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.createBean(AbstractAutowireCapableBeanFactory.java:495)
	at org.springframework.beans.factory.support.AbstractBeanFactory.lambda$doGetBean$0(AbstractBeanFactory.java:317)
	at org.springframework.beans.factory.support.DefaultSingletonBeanRegistry.getSingleton(DefaultSingletonBeanRegistry.java:222)
	at org.springframework.beans.factory.support.AbstractBeanFactory.doGetBean(AbstractBeanFactory.java:315)
	at org.springframework.beans.factory.support.AbstractBeanFactory.getBean(AbstractBeanFactory.java:199)
	at org.springframework.beans.factory.support.DefaultListableBeanFactory.preInstantiateSingletons(DefaultListableBeanFactory.java:759)
	at org.springframework.context.support.AbstractApplicationContext.finishBeanFactoryInitialization(AbstractApplicationContext.java:869)
	at org.springframework.context.support.AbstractApplicationContext.refresh(AbstractApplicationContext.java:550)
	at org.springframework.web.context.ContextLoader.configureAndRefreshWebApplicationContext(ContextLoader.java:409)
	at org.springframework.web.context.ContextLoader.initWebApplicationContext(ContextLoader.java:291)
	at org.springframework.web.context.ContextLoaderListener.contextInitialized(ContextLoaderListener.java:103)
	at org.apache.catalina.core.StandardContext.listenerStart(StandardContext.java:4939)
	at org.apache.catalina.core.StandardContext.startInternal(StandardContext.java:5434)
	at org.apache.catalina.util.LifecycleBase.start(LifecycleBase.java:150)
	at org.apache.catalina.core.ContainerBase$StartChild.call(ContainerBase.java:1559)
	at org.apache.catalina.core.ContainerBase$StartChild.call(ContainerBase.java:1549)
	at java.util.concurrent.FutureTask.run$$$capture(FutureTask.java:266)
	at java.util.concurrent.FutureTask.run(FutureTask.java)
	at java.util.concurrent.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1149)
	at java.util.concurrent.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor.java:624)
	at java.lang.Thread.run(Thread.java:748)
Caused by: java.lang.NoSuchMethodError: com.google.common.util.concurrent.MoreExecutors.sameThreadExecutor()Lcom/google/common/util/concurrent/ListeningExecutorService;
	at org.apache.curator.framework.listen.ListenerContainer.addListener(ListenerContainer.java:41)
	at org.apache.curator.framework.imps.CuratorFrameworkImpl.start(CuratorFrameworkImpl.java:257)
	at com.dangdang.ddframe.job.reg.zookeeper.ZookeeperRegistryCenter.init(ZookeeperRegistryCenter.java:98)
	at sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
	at sun.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:62)
	at sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
	at java.lang.reflect.Method.invoke(Method.java:498)
	at org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.invokeCustomInitMethod(AbstractAutowireCapableBeanFactory.java:1824)
	at org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.invokeInitMethods(AbstractAutowireCapableBeanFactory.java:1767)
	at org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.initializeBean(AbstractAutowireCapableBeanFactory.java:1695)
	... 22 more
```

排除项目中引入的guava 23.0，单独引入guava 18.0版本。

```xml
<dependencies>
        <!-- 引入elastic-job-lite核心模块 -->
        <dependency>
            <groupId>com.dangdang</groupId>
            <artifactId>elastic-job-lite-core</artifactId>
            <exclusions>
                <exclusion>
                    <artifactId>curator-framework</artifactId>
                    <groupId>org.apache.curator</groupId>
                </exclusion>
                <exclusion>
                    <artifactId>curator-recipes</artifactId>
                    <groupId>org.apache.curator</groupId>
                </exclusion>
                <exclusion>
                    <artifactId>guava</artifactId>
                    <groupId>com.google.guava</groupId>
                </exclusion>
            </exclusions>
        </dependency>
        <dependency>
            <groupId>org.apache.curator</groupId>
            <artifactId>curator-framework</artifactId>
            <version>2.10.0</version>
            <exclusions>
                <exclusion>
                    <artifactId>guava</artifactId>
                    <groupId>com.google.guava</groupId>
                </exclusion>
            </exclusions>
        </dependency>
        <dependency>
            <groupId>com.google.guava</groupId>
            <artifactId>guava</artifactId>
            <version>18.0</version>
        </dependency>

    </dependencies>
```