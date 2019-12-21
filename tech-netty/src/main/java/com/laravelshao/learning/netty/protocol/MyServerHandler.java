package com.laravelshao.learning.netty.protocol;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.nio.charset.Charset;
import java.util.UUID;

/**
 * 自定义服务端处理器
 *
 * @author qinghua.shao
 * @date 2019/12/21
 * @since 1.0.0
 */
public class MyServerHandler extends SimpleChannelInboundHandler<MyProtocol> {

    private int count;

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, MyProtocol msg) throws Exception {

        int length = msg.getLength();
        byte[] content = msg.getContent();

        System.out.println("服务端接收到的数据：");
        System.out.println("长度： " + length);
        System.out.println("内容： " + new String(content, Charset.forName("utf-8")));

        System.out.println("服务端接收到的消息数量: " + (++this.count));

        String respMsg = UUID.randomUUID().toString();
        int respLength = respMsg.getBytes("utf-8").length;
        byte[] respContent = respMsg.getBytes("utf-8");

        MyProtocol myProtocol = new MyProtocol();
        myProtocol.setLength(respLength);
        myProtocol.setContent(respContent);

        ctx.writeAndFlush(myProtocol);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
