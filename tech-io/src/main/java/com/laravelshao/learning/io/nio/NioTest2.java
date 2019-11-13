package com.laravelshao.learning.io.nio;

import java.io.FileInputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @author qinghua.shao
 * @date 2018/12/22
 * @since 1.0.0
 */
public class NioTest2 {

    public static void main(String[] args) throws Exception {

        FileInputStream fileInputStream = new FileInputStream("tech-io/NioTest2.txt");
        FileChannel fileChannel = fileInputStream.getChannel();

        ByteBuffer byteBuffer = ByteBuffer.allocate(512);
        fileChannel.read(byteBuffer);

        byteBuffer.flip();

        while (byteBuffer.remaining() > 0) {
            byte b = byteBuffer.get();
            System.out.println("Character: " + (char) b);
        }
    }
}
