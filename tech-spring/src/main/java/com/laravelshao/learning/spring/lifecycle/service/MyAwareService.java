package com.laravelshao.learning.spring.lifecycle.service;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanClassLoaderAware;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.context.*;
import org.springframework.context.annotation.ImportAware;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.type.AnnotationMetadata;

/**
 * Created by shaoqinghua on 2018/9/1.
 */
public class MyAwareService implements ApplicationContextAware,
        ApplicationEventPublisherAware, BeanClassLoaderAware, BeanFactoryAware,
        BeanNameAware, EnvironmentAware, ImportAware, ResourceLoaderAware {

    @Override
    public void setBeanClassLoader(ClassLoader classLoader) {
        System.out.println("MyAwareService->执行setBeanClassLoader方法");
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        System.out.println("MyAwareService->执行setBeanFactory方法");
    }

    @Override
    public void setBeanName(String s) {
        System.out.println("MyAwareService->执行setBeanName方法");
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        System.out.println("MyAwareService->执行setApplicationContext方法");
    }

    @Override
    public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        System.out.println("MyAwareService->执行setApplicationEventPublisher方法");
    }

    @Override
    public void setEnvironment(Environment environment) {
        System.out.println("MyAwareService->执行setEnvironment方法");
    }

    @Override
    public void setResourceLoader(ResourceLoader resourceLoader) {
        System.out.println("MyAwareService->执行setResourceLoader方法");
    }

    @Override
    public void setImportMetadata(AnnotationMetadata annotationMetadata) {
        System.out.println("MyAwareService->执行setImportMetadata方法");
    }
}
