package com.laravelshao.learning.io.nio;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @author qinghua.shao
 * @date 2019/11/30
 * @since 1.0.0
 */
public class NioTest8 {

    public static void main(String[] args) throws Exception {
        FileInputStream inputStream = new FileInputStream("tech-io/input2.txt");
        FileOutputStream outputStream = new FileOutputStream("tech-io/output2.txt");

        FileChannel inputChannel = inputStream.getChannel();
        FileChannel outputChannel = outputStream.getChannel();

        ByteBuffer buffer = ByteBuffer.allocateDirect(512);

        while (true) {
            buffer.clear();

            int read = inputChannel.read(buffer);
            System.out.println("read: " + read);

            if (-1 == read) {
                break;
            }

            buffer.flip();

            outputChannel.write(buffer);
        }

        inputChannel.close();
        outputChannel.close();
    }
}
