package com.laravelshao.learning.netty.websocket;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler;
import io.netty.handler.stream.ChunkedWriteHandler;

/**
 * 自定义 WebSocket 服务端初始化
 *
 * @author qinghua.shao
 * @date 2019/10/20
 * @since 1.0.0
 */
public class MyWebSocketServerInitializer extends ChannelInitializer<SocketChannel> {

    @Override
    protected void initChannel(SocketChannel socketChannel) throws Exception {

        ChannelPipeline pipeline = socketChannel.pipeline();

        pipeline.addLast(new HttpServerCodec());
        pipeline.addLast(new ChunkedWriteHandler());
        pipeline.addLast(new HttpObjectAggregator(8192));
        // 添加 WebSocket 服务协议处理器
        pipeline.addLast(new WebSocketServerProtocolHandler("/ws"));
        pipeline.addLast(new MyTextWebSocketFrameHandler());
    }
}
