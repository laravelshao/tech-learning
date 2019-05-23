package com.laravelshao.learning.netty.demo;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.util.CharsetUtil;
import lombok.extern.slf4j.Slf4j;

/**
 * @author shaoqinghua
 * @date 2018/12/23
 * @description netty服务端
 */
@Slf4j
public class NettyServer {

    private static final String IP = "127.0.0.1";

    private static final int PORT = 8080;

    private static final int BIZ_GROUP_SIZE = Runtime.getRuntime().availableProcessors() * 2;

    private static final int BIZ_THREAD_SIZE = 100;

    private static final EventLoopGroup BOSS_GROUP = new NioEventLoopGroup(BIZ_GROUP_SIZE);

    private static final EventLoopGroup WORKER_GROUP = new NioEventLoopGroup(BIZ_THREAD_SIZE);


    public static void main(String[] args) throws InterruptedException {

        NettyServer.start();
    }

    public static void start() throws InterruptedException {

        // 初始化启动类
        ServerBootstrap bootstrap = initServerBootstrap();

        // 绑定IP端口 设置同步使其阻塞
        ChannelFuture channelFuture = bootstrap.bind(IP, PORT).sync();

        // 监听服务器关闭并阻塞等待
        channelFuture.channel().closeFuture().sync();

        log.info("Netty服务启动，绑定端口:{}", PORT);
    }

    /**
     * 初始化服务端启动类
     *
     * @return
     */
    private static ServerBootstrap initServerBootstrap() {
        // 创建服务端启动类
        ServerBootstrap bootstrap = new ServerBootstrap();
        // 主从线程模型
        bootstrap.group(BOSS_GROUP, WORKER_GROUP)
                // 主线程处理类
                .channel(NioServerSocketChannel.class)
                // 子线程处理类
                .childHandler(new ChannelInitializer<Channel>() {
                    @Override
                    protected void initChannel(Channel channel) throws Exception {
                        ChannelPipeline pipeline = channel.pipeline();
                        pipeline.addLast(new LengthFieldBasedFrameDecoder(Integer.MAX_VALUE, 0, 4, 0, 4));
                        pipeline.addLast(new StringDecoder(CharsetUtil.UTF_8));
                        pipeline.addLast(new StringEncoder(CharsetUtil.UTF_8));
                        pipeline.addLast(new TcpServerHandler());
                    }
                });

        return bootstrap;
    }

    protected static void shutdown() {
        BOSS_GROUP.shutdownGracefully();
        WORKER_GROUP.shutdownGracefully();
    }

}
