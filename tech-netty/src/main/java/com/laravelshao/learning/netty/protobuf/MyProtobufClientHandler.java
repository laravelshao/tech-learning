package com.laravelshao.learning.netty.protobuf;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.util.Random;

/**
 * @author qinghua.shao
 * @date 2019/10/26
 * @since 1.0.0
 */
public class MyProtobufClientHandler extends SimpleChannelInboundHandler<MultiData.TypeMessage> {

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, MultiData.TypeMessage multiData) throws Exception {

    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {

        MultiData.TypeMessage typeMessage = null;

        int number = new Random().nextInt(3);
        if (number == 0) {
            typeMessage = MultiData.TypeMessage.newBuilder()
                    .setDataType(MultiData.TypeMessage.DataType.PersonType)
                    .setPerson(MultiData.Person.newBuilder().setName("cl").setAge(25).setAddress("上海").build())
                    .build();
        } else if (number == 1) {
            typeMessage = MultiData.TypeMessage.newBuilder()
                    .setDataType(MultiData.TypeMessage.DataType.DogType)
                    .setDog(MultiData.Dog.newBuilder().setName("dog").setAge(2).build())
                    .build();
        } else if (number == 2) {
            typeMessage = MultiData.TypeMessage.newBuilder()
                    .setDataType(MultiData.TypeMessage.DataType.CatType)
                    .setCat(MultiData.Cat.newBuilder().setName("cat").setCity("上海").build())
                    .build();
        }

        ctx.writeAndFlush(typeMessage);
    }
}
