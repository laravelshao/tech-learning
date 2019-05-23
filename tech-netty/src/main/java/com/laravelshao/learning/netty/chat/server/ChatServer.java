package com.laravelshao.learning.netty.chat.server;

import com.laravelshao.learning.netty.chat.server.handler.HttpHandler;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.stream.ChunkedWriteHandler;
import lombok.extern.slf4j.Slf4j;

/**
 * @author shaoqinghua
 * @date 2018/12/23
 * @description netty实现聊天服务端
 */
@Slf4j
public class ChatServer {

    private static final int port = 80;

    public static void main(String[] args) throws InterruptedException {

        // boss线程
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        // worker线程
        EventLoopGroup workerGroup = new NioEventLoopGroup();

        try {
            // netty服务
            ServerBootstrap bootstrap = new ServerBootstrap();
            // 主从模型
            bootstrap.group(bossGroup, workerGroup)
                    // 主线程处理类
                    .channel(NioServerSocketChannel.class)
                    // 子线程处理 handler
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel socketChannel) throws Exception {

                            ChannelPipeline pipeline = socketChannel.pipeline();
                            // 解析HTTP请求(包含编解码)
                            pipeline.addLast(new HttpServerCodec());
                            // 将多个消息转换为单一的FullHttpRequest对象(HttpServerCodec会将一个HTTP请求解析成多个消息对象)
                            pipeline.addLast(new HttpObjectAggregator(64 * 1024));
                            // 处理大数据流传输 直接传输1G文件会撑爆JVM内存
                            pipeline.addLast(new ChunkedWriteHandler());
                            // 自定义HTTP处理器
                            pipeline.addLast(new HttpHandler());

                        }
                    })
                    // 主线程配置
                    .option(ChannelOption.SO_BACKLOG, 128)
                    // 子线程配置
                    .childOption(ChannelOption.SO_KEEPALIVE, true);

            // 绑定服务端口 等待客户端连接
            ChannelFuture channelFuture = bootstrap.bind(port).sync(); // 设置同步使其阻塞

            log.info("Netty群聊服务启动，监听端口:{}", port);

            channelFuture.channel().closeFuture().sync();
        } finally {
            workerGroup.shutdownGracefully();
            bossGroup.shutdownGracefully();
        }
    }


}
