package com.laravelshao.learning.io.zerocopy;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.FileChannel;
import java.nio.channels.SocketChannel;

/**
 * 模拟零拷贝IO操作过程(客户端)
 * <p>
 * 测试文件 => jdk-8u212-macosx-x64.dmg
 * 结果 => 发送总字节数：264502407，耗时：94
 *
 * @author qinghua.shao
 * @date 2019/12/7
 * @since 1.0.0
 */
public class NewIOClient {

    public static void main(String[] args) throws IOException {

        SocketChannel socketChannel = SocketChannel.open();
        socketChannel.connect(new InetSocketAddress("localhost", 8899));
        socketChannel.configureBlocking(true);

        // 输入
        String fileName = "/Users/sylvia/Desktop/jdk-8u212-macosx-x64.dmg";
        FileChannel fileChannel = new FileInputStream(fileName).getChannel();

        long start = System.currentTimeMillis();

        // 输出
        // FileChannel.transferTo() => sendfile 系统调用
        long transferLen = fileChannel.transferTo(0, fileChannel.size(), socketChannel);

        System.out.println("发送总字节数：" + transferLen + "，耗时：" + (System.currentTimeMillis() - start));

        fileChannel.close();
    }
}
