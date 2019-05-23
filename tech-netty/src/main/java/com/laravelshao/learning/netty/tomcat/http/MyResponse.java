package com.laravelshao.learning.netty.tomcat.http;

import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.*;

import java.io.UnsupportedEncodingException;


/**
 * @author shaoqinghua
 * @date 2018/12/22
 * @description 自定义相应对象
 */
public class MyResponse {

    private ChannelHandlerContext ctx;
    private HttpRequest httpReq;

    public MyResponse(ChannelHandlerContext ctx, HttpRequest httpReq) {
        this.ctx = ctx;
        this.httpReq = httpReq;
    }

    public void write(String out) {
        try {
            if (out == null) {
                return;
            }
            FullHttpResponse response = new DefaultFullHttpResponse(
                    HttpVersion.HTTP_1_1, HttpResponseStatus.OK, Unpooled.wrappedBuffer(out.getBytes("UTF-8")));

            // 设置header参数
            response.headers().set(HttpHeaderNames.CONTENT_TYPE, "text/json");
            response.headers().set(HttpHeaderNames.CONTENT_LENGTH, response.content().readableBytes());
            response.headers().set(HttpHeaderNames.EXPIRES, 0);
            if (HttpUtil.isKeepAlive(httpReq)) {
                response.headers().set(HttpHeaderNames.KEEP_ALIVE, HttpHeaderValues.KEEP_ALIVE);
            }

            ctx.write(response);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } finally {
            ctx.flush();
        }
    }

}
