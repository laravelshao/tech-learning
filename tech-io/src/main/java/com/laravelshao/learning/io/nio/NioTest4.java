package com.laravelshao.learning.io.nio;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * NIO 方式从一个文件读取内容写入另一个文件
 *
 * @author qinghua.shao
 * @date 2019/11/30
 * @since 1.0.0
 */
public class NioTest4 {

    public static void main(String[] args) throws IOException {

        FileInputStream inputStream = new FileInputStream("tech-io/input.txt");
        FileOutputStream outputStream = new FileOutputStream("tech-io/output.txt");

        FileChannel inputChannel = inputStream.getChannel();
        FileChannel outputChannel = outputStream.getChannel();

        ByteBuffer buffer = ByteBuffer.allocate(512);

        while (true) {
            /**
             * 如果没有buffer.clear操作
             *
             * 1.position => 0，limit => 512，capacity => 512 初始化
             * 2.position => 27，limit => 512，capacity => 512 read后，读取到27个
             * 3.position => 0，limit => 27，capacity => 512 flip
             * 4.position => 27，limit => 27，capacity => 512 write
             * 再次read没有空间可以写入，返回read=0，因此会死循环，并且重复输出内容
             */
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
