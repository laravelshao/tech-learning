package com.laravelshao.learning.io.nio;

import java.nio.ByteBuffer;

/**
 * ByteBuffer类型化put与get方法
 *
 * @author qinghua.shao
 * @date 2019/11/30
 * @since 1.0.0
 */
public class NioTest5 {

    public static void main(String[] args) {

        ByteBuffer buffer = ByteBuffer.allocate(64);

        buffer.putInt(15);
        buffer.putLong(20000000L);
        buffer.putDouble(12.1123455);
        buffer.putChar('哈');
        buffer.putShort((short)2);
        buffer.putChar('我');

        buffer.flip();

        System.out.println(buffer.getInt());
        System.out.println(buffer.getLong());
        System.out.println(buffer.getDouble());
        System.out.println(buffer.getChar());
        System.out.println(buffer.getShort());
        System.out.println(buffer.getChar());
    }
}
