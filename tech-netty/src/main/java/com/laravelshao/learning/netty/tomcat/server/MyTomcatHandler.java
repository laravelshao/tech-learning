package com.laravelshao.learning.netty.tomcat.server;

import com.laravelshao.learning.netty.tomcat.http.MyRequest;
import com.laravelshao.learning.netty.tomcat.http.MyResponse;
import com.laravelshao.learning.netty.tomcat.servlet.ExecServlet;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.codec.http.HttpRequest;

/**
 * @author shaoqinghua
 * @date 2018/12/22
 * @description 自定义tomcat处理器类
 */
public class MyTomcatHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        if (msg instanceof HttpRequest) {
            HttpRequest httpReq = (HttpRequest) msg;
            MyRequest request = new MyRequest(ctx, httpReq);
            MyResponse response = new MyResponse(ctx, httpReq);

            new ExecServlet().doGet(request, response);
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
