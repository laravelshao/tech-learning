package com.laravelshao.learning.netty.handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageDecoder;

import java.util.List;

/**
 * 自定义Long类型转String解码器
 *
 * @author qinghua.shao
 * @date 2019/12/21
 * @since 1.0.0
 */
public class MyLongToStringDecoder extends MessageToMessageDecoder<Long> {

    @Override
    protected void decode(ChannelHandlerContext ctx, Long msg, List out) throws Exception {
        System.out.println("MyLongToStringDecoder invoked!");
        out.add(String.valueOf(msg));
    }
}
