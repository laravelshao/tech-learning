package com.laravelshao.learning.io.nio.aaabbbccc;

import java.io.FileInputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @author qinghua.shao
 * @date 2020/5/30
 * @since 1.0.0
 */
public class NioTest2 {

    public static void main(String[] args) throws Exception {

        FileInputStream fileInputStream = new FileInputStream("tech-io/NioTest2.txt");
        FileChannel fileChannel = fileInputStream.getChannel();

        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
        fileChannel.read(byteBuffer);

        byteBuffer.flip();

        while (byteBuffer.remaining() > 0) {
            System.out.println((char) byteBuffer.get());
        }
    }
}
