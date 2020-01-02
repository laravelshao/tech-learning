package com.laravelshao.learning.io.bio;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author shaoqinghua
 * @date 2018/12/22
 * @description 阻塞IO服务端
 */
public class BIOServer {

    /**
     * 监听端口
     */
    private static final int port = 8080;

    public static void main(String[] args) throws IOException {

        // 启动服务端
        ServerSocket server = new ServerSocket(port);
        //server.setSoTimeout(1000); // 通过设置超时时间可以使服务端变成非阻塞
        System.out.println("BIO服务启动，监听端口:" + port);

        // 死循环监听服务
        while (true) {
            // 等待客户端连接(阻塞方法)
            Socket client = server.accept();
            // 获取输入流
            InputStream is = client.getInputStream();
            // 以字节数组来读取 非严格意义上缓存区
            byte[] buffer = new byte[1024];
            int len = 0;
            while ((len = is.read(buffer)) != -1) {
                String msg = new String(buffer, 0, len);
                System.out.println(msg);
            }
        }
    }

}
