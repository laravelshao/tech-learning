package com.laravelshao.learning.spi.dubbo;

import org.apache.dubbo.common.extension.ExtensionLoader;
import org.apache.dubbo.rpc.Protocol;

/**
 * @author shaoqinghua
 * @date 2018/10/28
 * @description Dubbo SPI测试
 */
public class DubboSpiTest {

    public static void main(String[] args) {
        /**
         * Dubbo SPI规范
         * 对JDK SPI规范类似，仅有两个不同
         * 1.需要在resource目录下配置(META-INF/dubbo或META-INF/dubbo/internal或META-INF/services
         * 2.在上述目录下并基于SPI接口全路径名称创建文件，内容为KEY对应Value形式
         */
        Protocol myProtocol = ExtensionLoader
                .getExtensionLoader(Protocol.class).getExtension("myProtocol");
        System.out.println(myProtocol.getDefaultPort());

        Protocol protocol = ExtensionLoader.getExtensionLoader(Protocol.class).getAdaptiveExtension();
    }
}
