package com.laravelshao.learning.spi.jdk;

import java.util.ServiceLoader;

/**
 * @author shaoqinghua
 * @date 2018/7/16
 * @description
 */
public class JdkSpiTest {

    public static void main(String[] args) {
        /**
         * JDK SPI规范
         * 1.需要在classpath下创建目录，目录命名必须是：META-INF/services
         * 2.在该目录下创建一个文件，该文件需满足以下几个条件
         *  a.文件名必须是扩展接口的全路径名称
         *  b.文件内部描述的是该扩展接口的实现类
         *  c.文件的编码格式是UTF-8
         * 3.通过java.util.ServiceLoader的加载机制来发现
         */
        ServiceLoader<ISayName> loaders = ServiceLoader.load(ISayName.class);
        for (ISayName sayName : loaders) {
            sayName.say();
        }
    }
}