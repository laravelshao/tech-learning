package com.laravelshao.learning.io.nio;


import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

/**
 * @author shaoqinghua
 * @date 2018/12/22
 * @description 非阻塞IO服务端
 */
public class NIOServer {

    /**
     * 监听端口
     */
    private static final int port = 8080;

    private static Selector selector;

    public static void main(String[] args) throws IOException {

        InetSocketAddress address = new InetSocketAddress(port);

        // 创建服务
        ServerSocketChannel server = ServerSocketChannel.open();
        server.bind(address);

        // 配置为非阻塞(默认阻塞)
        server.configureBlocking(false);

        // 开启监听服务
        selector = Selector.open();
        server.register(selector, SelectionKey.OP_ACCEPT);
        System.out.println("NIO服务启动完成，监听端口:" + port);

        // 轮询
        while (true) {
            int wait = selector.select();
            if (wait == 0) {
                continue;
            }

            Set<SelectionKey> keys = selector.selectedKeys();
            Iterator<SelectionKey> it = keys.iterator();
            while (it.hasNext()) {
                SelectionKey key = it.next();
                process(key);
                it.remove();
            }

        }
    }


    /**
     * 处理
     *
     * @param key
     */
    private static void process(SelectionKey key) throws IOException {

        ByteBuffer buffer = ByteBuffer.allocate(1024);

        if (key.isAcceptable()) {
            ServerSocketChannel server = (ServerSocketChannel) key.channel();
            SocketChannel client = server.accept();
            client.configureBlocking(false);
            client.register(selector, SelectionKey.OP_READ);
        } else if (key.isReadable()) {
            SocketChannel client = (SocketChannel) key.channel();
            int len = client.read(buffer);
            if (len > 0) {
                buffer.flip();
                String content = new String(buffer.array(), 0, len);
                System.out.println(content);
                client.register(selector, SelectionKey.OP_WRITE);
            }
            buffer.clear();
        } else if (key.isWritable()) {
            SocketChannel client = (SocketChannel) key.channel();
            client.write(ByteBuffer.wrap("hello world".getBytes()));
            client.close();
        }
    }

}
