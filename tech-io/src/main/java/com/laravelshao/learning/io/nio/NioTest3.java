package com.laravelshao.learning.io.nio;

import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @author qinghua.shao
 * @date 2018/12/22
 * @since 1.0.0
 */
public class NioTest3 {

    public static void main(String[] args) throws Exception {

        FileOutputStream fileOutputStream = new FileOutputStream("tech-io/NioTest3.txt");
        FileChannel fileChannel = fileOutputStream.getChannel();

        ByteBuffer byteBuffer = ByteBuffer.allocate(512);

        byte[] messages = "hello world welcome".getBytes();

        for (int i = 0; i < messages.length; i++) {
            byteBuffer.put(messages[i]);
        }

        byteBuffer.flip();

        fileChannel.write(byteBuffer);

        fileOutputStream.close();
    }
}
