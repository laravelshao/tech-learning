package com.laravelshao.learning.io.nio;

import java.nio.IntBuffer;
import java.security.SecureRandom;

/**
 * @author qinghua.shao
 * @date 2018/12/22
 * @since 1.0.0
 */
public class NioTest1 {

    public static void main(String[] args) {
        IntBuffer buffer = IntBuffer.allocate(10);

        for (int i = 0; i < buffer.capacity(); i++) {
            int randomNumber = new SecureRandom().nextInt(20);
            buffer.put(randomNumber);
        }

        buffer.flip();

        while (buffer.hasRemaining()) {
            System.out.println(buffer.get());
        }
    }
}
