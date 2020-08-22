package com.laravelshao.learning.io.nio.aaabbbccc;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @author qinghua.shao
 * @date 2020/5/30
 * @since 1.0.0
 */
public class NioTest4 {

    public static void main(String[] args) throws Exception {
        FileInputStream fileInputStream = new FileInputStream("tech-io/input.txt");
        FileOutputStream fileOutputStream = new FileOutputStream("tech-io/output.txt");

        FileChannel inputChannel = fileInputStream.getChannel();
        FileChannel outputChannel = fileOutputStream.getChannel();

        ByteBuffer byteBuffer = ByteBuffer.allocate(512);

        while (true) {
            byteBuffer.clear();
            int read = inputChannel.read(byteBuffer);
            System.out.println("read = " + read);
            if (read == -1) {
                break;
            }
            byteBuffer.flip();

            outputChannel.write(byteBuffer);

        }

        inputChannel.close();
        outputChannel.close();
    }
}
