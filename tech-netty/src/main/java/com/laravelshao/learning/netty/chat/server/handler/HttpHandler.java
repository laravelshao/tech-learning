package com.laravelshao.learning.netty.chat.server.handler;

import io.netty.channel.*;
import io.netty.handler.codec.http.*;
import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.RandomAccessFile;
import java.net.URISyntaxException;
import java.net.URL;

/**
 * @author shaoqinghua
 * @date 2018/12/23
 * @description 自定义HTTP处理器
 */
@Slf4j
public class HttpHandler extends SimpleChannelInboundHandler<FullHttpRequest> {

    /**
     * 获取class路径
     */
    private URL baseURL = HttpHandler.class.getProtectionDomain().getCodeSource().getLocation();

    private final String WEB_ROOT = "webroot";

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, FullHttpRequest request) throws Exception {

        String uri = request.uri();
        String page = uri.equals("/") ? "chat.html" : uri;

        RandomAccessFile file;
        try {
            file = new RandomAccessFile(getResource(page), "r");
        } catch (Exception e) {
            ctx.fireChannelRead(request.retain());
            return;
        }

        HttpResponse response = new DefaultHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.OK);

        String contentType = "text/html;";
        if (uri.endsWith(".css")) {
            contentType = "text/css";
        } else if (uri.endsWith(".js")) {
            contentType = "text/javascript";
        } else if (uri.toLowerCase().matches("^(jpg|png|gif)$")) {
            String ext = uri.substring(uri.lastIndexOf("."));
            contentType = "image/" + ext;
        }

        response.headers().set(HttpHeaderNames.CONTENT_TYPE, contentType + "charset=UTF-8;");
        boolean keepAlive = HttpUtil.isKeepAlive(request);
        if (keepAlive) {
            response.headers().set(HttpHeaderNames.CONTENT_LENGTH, file.length());
            response.headers().set(HttpHeaderNames.KEEP_ALIVE, HttpHeaderValues.KEEP_ALIVE);
        }

        ctx.write(response);
        ctx.write(new DefaultFileRegion(file.getChannel(), 0, file.length()));

        ChannelFuture future = ctx.writeAndFlush(LastHttpContent.EMPTY_LAST_CONTENT);
        if (!keepAlive) {
            future.addListener(ChannelFutureListener.CLOSE);
        }

        file.close();
    }

    private File getResource(String fileName) throws URISyntaxException {

        String path = baseURL.toURI() + WEB_ROOT + "/" + fileName;
        path = path.replace("file:", "").replace("//", "/");
        return new File(path);
    }
}
