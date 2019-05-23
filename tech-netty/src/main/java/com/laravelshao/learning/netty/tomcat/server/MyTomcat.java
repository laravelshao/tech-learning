package com.laravelshao.learning.netty.tomcat.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.HttpRequestDecoder;
import io.netty.handler.codec.http.HttpResponseEncoder;
import lombok.extern.slf4j.Slf4j;

/**
 * @author shaoqinghua
 * @date 2018/12/22
 * @description netty实现tomcat
 */
@Slf4j
public class MyTomcat {

    private static final int port = 8080;

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
                            // 业务逻辑链路
                            // 服务端发送HttpResponse 需要使用HttpResponseEncoder编码
                            socketChannel.pipeline().addLast(new HttpResponseEncoder());
                            // 服务端接收HttpRequest 需要使用HttpRequestDecoder解码
                            socketChannel.pipeline().addLast(new HttpRequestDecoder());
                            // 自定义处理器
                            socketChannel.pipeline().addLast(new MyTomcatHandler());
                        }
                    })
                    // 主线程配置
                    .option(ChannelOption.SO_BACKLOG, 128)
                    // 子线程配置
                    .childOption(ChannelOption.SO_KEEPALIVE, true);

            // 绑定服务端口
            ChannelFuture channelFuture = bootstrap.bind(port).sync(); // 设置同步使其阻塞

            log.info("MyTomcat服务启动，监听端口:{}", port);

            // 接收请求
            channelFuture.channel().closeFuture().sync();
        } finally {
            workerGroup.shutdownGracefully();
            bossGroup.shutdownGracefully();
        }
    }

}
