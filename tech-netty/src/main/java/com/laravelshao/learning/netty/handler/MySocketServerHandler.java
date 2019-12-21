package com.laravelshao.learning.netty.handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * 自定义 Socket 服务端处理器
 *
 * @author qinghua.shao
 * @date 2019/10/20
 * @since 1.0.0
 */
public class MySocketServerHandler extends SimpleChannelInboundHandler<Long> {

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, Long msg) throws Exception {

        System.out.println(channelHandlerContext.channel().remoteAddress() + " : " + msg);
        channelHandlerContext.writeAndFlush(654321L);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        // 连接异常执行关闭连接操作
        ctx.close();
    }
}
