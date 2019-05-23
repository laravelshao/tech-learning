package com.laravelshao.learning.netty.demo;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.LengthFieldPrepender;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.util.CharsetUtil;

/**
 * @author shaoqinghua
 * @date 2018/12/23
 * @description netty客户端
 */
public class NettyClient implements Runnable {

    public static void main(String[] args) {

        for (int i = 0; i < 3; i++) {
            new Thread(new NettyClient(), ">>>>> Thread " + i).start();
        }
    }

    @Override
    public void run() {

        EventLoopGroup group = new NioEventLoopGroup();
        try {
            Bootstrap bootstrap = new Bootstrap();
            bootstrap.group(group)
                    .channel(NioSocketChannel.class)
                    .handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel socketChannel) throws Exception {
                            ChannelPipeline pipeline = socketChannel.pipeline();
                            pipeline.addLast(new LengthFieldPrepender(4));
                            pipeline.addLast(new StringDecoder(CharsetUtil.UTF_8));
                            pipeline.addLast(new StringEncoder(CharsetUtil.UTF_8));
                            pipeline.addLast(new MyClientHandler());

                        }
                    })
                    .option(ChannelOption.TCP_NODELAY, true);

            for (int i = 0; i < 10; i++) {
                ChannelFuture future = bootstrap.connect("127.0.0.1", 8080).sync();
                future.channel().writeAndFlush("hello service " + Thread.currentThread().getName() + " -----> "+i);
                future.channel().closeFuture().sync();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            group.shutdownGracefully();
        }
    }

}
