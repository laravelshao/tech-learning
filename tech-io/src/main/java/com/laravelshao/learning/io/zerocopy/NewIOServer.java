package com.laravelshao.learning.io.zerocopy;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

/**
 * 模拟零拷贝IO操作过程(服务端)
 *
 * @author qinghua.shao
 * @date 2019/12/7
 * @since 1.0.0
 */
public class NewIOServer {

    public static void main(String[] args) throws IOException {

        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        ServerSocket serverSocket = serverSocketChannel.socket();
        /**
         * 当一个连接被关闭，在一段时间内处于超时等待(TIME_WAIT)状态，将不能立即绑定上，
         * 如果在超时等待期间，也允许绑定，需要设置为true
         */
        serverSocket.setReuseAddress(true);
        serverSocket.bind(new InetSocketAddress(8899));

        ByteBuffer byteBuffer = ByteBuffer.allocate(4096);

        while (true) {
            SocketChannel socketChannel = serverSocketChannel.accept();
            socketChannel.configureBlocking(true);

            int len = 0;
            try {
                while ((len = socketChannel.read(byteBuffer)) != -1) {

                    // do something
                    // 重置
                    byteBuffer.rewind();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
