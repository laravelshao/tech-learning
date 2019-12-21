package com.laravelshao.learning.netty.protocol;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.nio.charset.Charset;

/**
 * 自定义客户端处理器
 *
 * @author qinghua.shao
 * @date 2019/12/21
 * @since 1.0.0
 */
public class MyClientHandler extends SimpleChannelInboundHandler<MyProtocol> {

    private int count;

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {

        for (int i = 0; i < 10; i++) {
            String msgToSent = "sent from client";
            byte[] content = msgToSent.getBytes("utf-8");
            int length = content.length;

            MyProtocol myProtocol = new MyProtocol();
            myProtocol.setLength(length);
            myProtocol.setContent(content);

            ctx.writeAndFlush(myProtocol);
        }
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, MyProtocol msg) throws Exception {

        int length = msg.getLength();
        byte[] content = msg.getContent();

        System.out.println("客户端接收到的数据：");
        System.out.println("长度： " + length);
        System.out.println("内容： " + new String(content, Charset.forName("utf-8")));

        System.out.println("客户端接收到的消息数量: " + (++this.count));
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
