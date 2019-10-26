package com.laravelshao.learning.netty.protobuf;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * 自定义 protobuf 服务端处理器
 *
 * @author qinghua.shao
 * @date 2019/10/26
 * @since 1.0.0
 */
public class MyProtobufServerHandler extends SimpleChannelInboundHandler<MultiData.TypeMessage> {

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, MultiData.TypeMessage msg) throws Exception {

        MultiData.TypeMessage.DataType dataType = msg.getDataType();

        if (dataType == MultiData.TypeMessage.DataType.PersonType) {
            MultiData.Person person = msg.getPerson();
            System.out.println(person.getName());
            System.out.println(person.getAge());
            System.out.println(person.getAddress());
        } else if (dataType == MultiData.TypeMessage.DataType.DogType) {
            MultiData.Dog dog = msg.getDog();
            System.out.println(dog.getName());
            System.out.println(dog.getAge());
        } else {
            MultiData.Cat cat = msg.getCat();
            System.out.println(cat.getName());
            System.out.println(cat.getCity());
        }
    }
}
