package com.laravelshao.learning.netty.handler;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

/**
 * 自定义Long类型转字节编码器
 *
 * @author qinghua.shao
 * @date 2019/12/21
 * @since 1.0.0
 */
public class MyLongToByteEncoder extends MessageToByteEncoder<Long> {

    @Override
    protected void encode(ChannelHandlerContext ctx, Long msg, ByteBuf out) throws Exception {

        System.out.println("MyLongToByteEncoder invoked!");
        System.out.println(msg);

        out.writeLong(msg);
    }
}
