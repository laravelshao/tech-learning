package com.laravelshao.spring.framework.context.support;

import com.laravelshao.spring.framework.beans.BeanDefinition;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * 对配置文件进行查找、加载、解析
 * Created by shaoqinghua on 2018/8/15.
 */
public class BeanDefinitionReader {

    private static final String SCAN_PACKAGE = "scanPackage";

    private Properties config = new Properties();

    private List<String> registyBeanClasses = new ArrayList<>();

    public BeanDefinitionReader(String[] configLocations) {

        InputStream is = this.getClass().getClassLoader().getResourceAsStream(
                configLocations[0].replace("classpath:", ""));

        try {
            config.load(is);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (null != is) {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        // 执行加载bean
        doLoadBeanDefinitions(config.getProperty(SCAN_PACKAGE));
    }

    /**
     * 加载Bean Definition
     *
     * @return
     */
    public List<String> loadBeanDefinitions() {
        return this.registyBeanClasses;
    }

    /**
     * 注册BeanDefinition
     *
     * @return
     */
    public BeanDefinition registerBeanDefinition(String className) {

        if (this.registyBeanClasses.contains(className)) {
            BeanDefinition beanDefinition = new BeanDefinition();
            beanDefinition.setBeanClassName(className);
            beanDefinition.setFactoryBeanName(
                    lowerFirstCase(className.substring(className.lastIndexOf(".") + 1)));

            return beanDefinition;
        }

        return null;
    }

    /**
     * 执行加载
     */
    private void doLoadBeanDefinitions(String packageName) {

        URL url = this.getClass().getClassLoader().getResource("/" + packageName.replaceAll("\\.", "/"));

        File classDir = new File(url.getFile());
        for (File file : classDir.listFiles()) {
            if (file.isDirectory()) {
                doLoadBeanDefinitions(packageName + "." + file.getName());
            } else {
                registyBeanClasses.add(packageName + "." + file.getName().replace(".class", ""));
            }
        }
    }

    private String lowerFirstCase(String str) {
        char[] chars = str.toCharArray();
        chars[0] += 32;
        return String.valueOf(chars);
    }

    public Properties getConfig(){
        return this.config;
    }

}
