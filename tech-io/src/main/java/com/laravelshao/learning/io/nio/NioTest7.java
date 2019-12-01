package com.laravelshao.learning.io.nio;

import java.nio.ByteBuffer;

/**
 * 只读buffer，可以将一个普通Buffer调用asReadOnlyBuffer方法返回一个只读Buffer，
 * 但不能将一个只读Buffer转换为读写Buffer
 *
 * @author qinghua.shao
 * @date 2019/11/30
 * @since 1.0.0
 */
public class NioTest7 {

    public static void main(String[] args) {

        ByteBuffer buffer = ByteBuffer.allocate(10);

        for (int i = 0; i < buffer.capacity(); i++) {
            buffer.put((byte) i);
        }

        ByteBuffer readonlyBuffer = buffer.asReadOnlyBuffer();
        System.out.println(readonlyBuffer.getClass());

        readonlyBuffer.position(0);

        //readonlyBuffer.put((byte) 2);
    }
}
