package com.laravelshao.learning.io.bio;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.util.UUID;

/**
 * @author shaoqinghua
 * @date 2018/12/22
 * @description 阻塞IO客户端
 */
public class BIOClient {

    public static void main(String[] args) {
        try {
            // 创建客户端连接
            Socket socket = new Socket("localhost", 8080);
            // 获取输出流
            OutputStream os = socket.getOutputStream();

            String msg = UUID.randomUUID().toString();
            // 发送消息
            os.write(msg.getBytes());

            // 关闭流和连接
            os.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
