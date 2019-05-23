package com.laravelshao.spring.framework.context;

import com.laravelshao.spring.framework.annotation.Autowired;
import com.laravelshao.spring.framework.annotation.Controller;
import com.laravelshao.spring.framework.annotation.Service;
import com.laravelshao.spring.framework.aop.AopConfig;
import com.laravelshao.spring.framework.beans.BeanDefinition;
import com.laravelshao.spring.framework.beans.BeanPostProcessor;
import com.laravelshao.spring.framework.beans.BeanWrapper;
import com.laravelshao.spring.framework.context.support.BeanDefinitionReader;
import com.laravelshao.spring.framework.core.BeanFactory;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by shaoqinghua on 2018/8/15.
 */
public class ApplicationContext extends DefaultListableBeanFactory implements BeanFactory {

    private String[] configLocations;

    private BeanDefinitionReader reader;

    ///**
    // * BeanDefinition存储集合
    // */
    //private Map<String, BeanDefinition> beanDefinitionMap = new ConcurrentHashMap<>();

    /**
     * 单例类缓存集合
     */
    private Map<String, Object> beanCacheMap = new ConcurrentHashMap<>();

    /**
     * 存储所有被代理对象集合
     */
    private Map<String, BeanWrapper> beanWrapperMap = new ConcurrentHashMap<>();


    public ApplicationContext(String... configLocations) {
        this.configLocations = configLocations;
        refresh();
    }


    public void refresh() {
        /**
         * Resource定位
         */
        reader = new BeanDefinitionReader(configLocations);

        /**
         * BeanDefinition加载
         */
        List<String> beanDefinitions = reader.loadBeanDefinitions();

        /**
         * BeanDefinition注册
         */
        doRegisterBeanDefinitions(beanDefinitions);

        /**
         * 依赖注入(lazy-init = false) 会执行依赖注入并调用getBean
         */
        doAutowired();


        //DemoController demoController = (DemoController) this.getBean("demoController");
        //demoController.query(null, null, "laravelshao");
    }

    /**
     * 执行依赖注入
     */
    private void doAutowired() {

        for (Map.Entry<String, BeanDefinition> beanDefinitionEntry : this.beanDefinitionMap.entrySet()) {
            String beanName = beanDefinitionEntry.getKey();
            if (!beanDefinitionEntry.getValue().isLazyInit()) {
                // 调用getBean实例化
                Object obj = getBean(beanName);
                System.out.println(obj.getClass());
            }
        }

        for (Map.Entry<String, BeanWrapper> beanWrapperEntry : this.beanWrapperMap.entrySet()) {
            populateBean(beanWrapperEntry.getKey(), beanWrapperEntry.getValue().getOriginalInstance());
        }
    }

    private void populateBean(String beanName, Object instance) {
        Class<?> clazz = instance.getClass();
        if (!(clazz.isAnnotationPresent(Controller.class) || clazz.isAnnotationPresent(Service.class))) {
            return;
        }

        Field[] fields = clazz.getDeclaredFields();

        for (Field field : fields) {
            if (!field.isAnnotationPresent(Autowired.class)) {
                continue;
            }
            Autowired autowired = field.getAnnotation(Autowired.class);
            String autowiredBeanName = autowired.value().trim();
            if ("".equals(autowiredBeanName)) {
                autowiredBeanName = field.getType().getName();
            }
            field.setAccessible(true);

            try {
                field.set(instance, this.beanWrapperMap.get(autowiredBeanName).getWrappedInstance());
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * BeanDefinition注册
     * <p>
     * 将DeanDefinition真正注册到beanDefinitionMap
     *
     * @param beanDefinitions
     */
    private void doRegisterBeanDefinitions(List<String> beanDefinitions) {

        /**
         * beanName有三种情况
         * 1 默认是类名首字母小写
         * 2 自定义名字
         * 3 接口注入
         */
        try {
            for (String className : beanDefinitions) {
                Class<?> clazz = Class.forName(className);

                // 接口不能实例化 需实现类实例化
                if (clazz.isInterface()) {
                    continue;
                }

                BeanDefinition beanDefinition = reader.registerBeanDefinition(className);
                if (beanDefinition != null) {
                    this.beanDefinitionMap.put(beanDefinition.getFactoryBeanName(), beanDefinition);
                }

                Class<?>[] interfaces = clazz.getInterfaces();
                for (Class inter : interfaces) {
                    this.beanDefinitionMap.put(inter.getName(), beanDefinition);
                }

                // 容器初始化完成
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }


    /**
     * 依赖注入入口
     * 读取BeanDefinition信息 通过反射机制创建一个实例并返回
     * <p>
     * Spring实现：不会暴露最原始对象 会利用BeanWrapper进行包装
     * 装饰器模式：
     * 1 保留原来的OOP关系
     * 2 我需要对它进行扩展，增强（为了以后AOP打基础）
     *
     * @param beanName
     * @return
     */
    @Override
    public Object getBean(String beanName) {

        BeanDefinition beanDefinition = this.beanDefinitionMap.get(beanName);

        try {
            // 生成通知事件
            BeanPostProcessor postProcessor = new BeanPostProcessor();
            Object instance = instantiateBean(beanDefinition);
            if (instance == null) {
                return null;
            }

            // 在实例初始化前调用一次
            postProcessor.postProcessBeforeInitialization(instance, beanName);

            BeanWrapper beanWrapper = new BeanWrapper(instance);
            beanWrapper.setAopConfig(instantiateAopConfig(beanDefinition));
            beanWrapper.setPostProcessor(postProcessor);
            this.beanWrapperMap.put(beanName, beanWrapper);

            // 在实例初始化后调用一次
            postProcessor.postProcessAfterInitialization(instance, beanName);

            return this.beanWrapperMap.get(beanName).getWrappedInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     * 初始化AOP配置
     *
     * @param beanDefinition
     * @return
     */
    private AopConfig instantiateAopConfig(BeanDefinition beanDefinition) throws Exception {

        AopConfig aopConfig = new AopConfig();

        String expression = reader.getConfig().getProperty("pointCut");
        String[] before = reader.getConfig().getProperty("aspectBefore").split("\\s");
        String[] after = reader.getConfig().getProperty("aspectAfter").split("\\s");

        String beanName = beanDefinition.getBeanClassName();
        Class<?> clazz = Class.forName(beanName);
        Class<?> aspectClass = Class.forName(before[0]);

        Pattern pattern = Pattern.compile(expression);

        for (Method m : clazz.getMethods()) {
            /**
             * public .* com\.gupaoedu\.vip\.spring\.demo\.service\..*Service\..*\(.*\)
             * public java.lang.String com.laravelshao.spring.api.service.DemoService.get(java.lang.String)
             */
            Matcher matcher = pattern.matcher(m.toString());
            if (matcher.matches()) {
                // 满足切面规则添加到AOP配置中
                aopConfig.put(m, aspectClass.newInstance(),
                        new Method[]{aspectClass.getMethod(before[1]), aspectClass.getMethod(after[1])});
            }
        }

        return aopConfig;
    }

    /**
     * 使用beanDefinition实例化对象
     *
     * @param beanDefinition
     * @return
     */
    private Object instantiateBean(BeanDefinition beanDefinition) {
        Object instance;
        String className = beanDefinition.getBeanClassName();
        try {
            // 根据class确定类是否有实例
            if (this.beanCacheMap.containsKey(className)) {
                instance = this.beanCacheMap.get(className);
            } else {
                Class<?> clazz = Class.forName(className);
                instance = clazz.newInstance();
                this.beanCacheMap.put(className, instance);
            }
            return instance;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public String[] getBeanDefinitionNames() {
        return this.beanDefinitionMap.keySet().toArray(new String[this.beanDefinitionMap.size()]);
    }

    public Properties getConfig() {
        return this.reader.getConfig();
    }

}
