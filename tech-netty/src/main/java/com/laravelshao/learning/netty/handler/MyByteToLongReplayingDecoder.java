package com.laravelshao.learning.netty.handler;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import io.netty.handler.codec.ReplayingDecoder;

import java.util.List;

/**
 * 自定义字节转Long类型ReplayingDecoder
 *
 * <p>相比 {@link ByteToMessageDecoder} 而言，{@link ReplayingDecoder} 不需要开发者
 * 手动判断 {@link ByteBuf} 中数据是否足够。
 *
 * @author qinghua.shao
 * @date 2019/12/21
 * @since 1.0.0
 */
public class MyByteToLongReplayingDecoder extends ReplayingDecoder {

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {

        System.out.println("MyByteToLongDecoder invoked!");
        System.out.println("可读字节数: " + in.readableBytes());

        out.add(in.readLong());
    }
}
