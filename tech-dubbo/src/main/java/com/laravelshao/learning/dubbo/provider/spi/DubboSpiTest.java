package com.laravelshao.learning.dubbo.provider.spi;

import com.alibaba.dubbo.common.extension.ExtensionLoader;
import com.alibaba.dubbo.rpc.Protocol;

/**
 * @author shaoqinghua
 * @date 2018/7/7
 * @description Dubbo SPI 测试
 */
public class DubboSpiTest {

    public static void main(String[] args) {

        Protocol protocol = ExtensionLoader.getExtensionLoader(Protocol.class).getAdaptiveExtension();
    }
}
