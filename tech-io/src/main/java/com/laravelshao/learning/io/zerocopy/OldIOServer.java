package com.laravelshao.learning.io.zerocopy;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 模拟旧的IO操作过程(服务端)
 *
 * @author qinghua.shao
 * @date 2019/12/7
 * @since 1.0.0
 */
public class OldIOServer {

    public static void main(String[] args) throws IOException {

        ServerSocket serverSocket = new ServerSocket(8899);

        while (true) {
            Socket socket = serverSocket.accept(); // 监听连接(阻塞方法)
            DataInputStream dataInputStream = new DataInputStream(socket.getInputStream());

            byte[] bytes = new byte[4096];
            try {
                while (dataInputStream.read(bytes) != -1) {
                    // do something
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
