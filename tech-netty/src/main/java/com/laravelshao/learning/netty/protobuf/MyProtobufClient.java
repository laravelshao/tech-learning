package com.laravelshao.learning.netty.protobuf;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

/**
 * 自定义 protobuf 客户端
 *
 * @author qinghua.shao
 * @date 2019/10/26
 * @since 1.0.0
 */
public class MyProtobufClient {

    public static void main(String[] args) throws InterruptedException {

        NioEventLoopGroup eventLoopGroup = new NioEventLoopGroup();
        try {
            Bootstrap bootstrap = new Bootstrap();
            bootstrap.group(eventLoopGroup).channel(NioSocketChannel.class)
                    .handler(new MyProtobufClientInitializer());

            ChannelFuture channelFuture = bootstrap.connect("localhost", 8080).sync();
            channelFuture.channel().closeFuture().sync();
        } finally {
            eventLoopGroup.shutdownGracefully();
        }
    }
}
