package com.laravelshao.learning.netty.handler;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;

/**
 * 自定义字节转Long类型解码器
 *
 * @author qinghua.shao
 * @date 2019/12/21
 * @since 1.0.0
 */
public class MyByteToLongDecoder extends ByteToMessageDecoder {

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {

        System.out.println("MyByteToLongDecoder invoked!");
        System.out.println("可读字节数: " + in.readableBytes());

        /*** 在解码器进行数据解码时，一定要判断缓存(ByteBuf)中的数据是否足够，否则将会产生一些问题 */
        if (in.readableBytes() >= 8) {
            out.add(in.readLong());
        }
    }
}
