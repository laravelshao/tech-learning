package com.laravelshao.learning.rpc.manual.client;

import com.laravelshao.learning.rpc.manual.service.RpcRequest;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 * @author shaoqinghua
 * @date 2018/10/5
 * @description
 */
public class TCPTransport {

    private String host;
    private int port;

    public TCPTransport(String host, int port) {
        this.host = host;
        this.port = port;
    }

    /**
     * 创建Socket连接
     *
     * @return
     */
    private Socket newSocket() {
        System.out.println("创建一个新的连接");
        Socket socket;
        try {
            socket = new Socket(host, port);
            return socket;
        } catch (IOException e) {
            throw new RuntimeException("创建连接失败");
        }
    }

    /**
     * 发送请求
     *
     * @param request
     * @return
     */
    public Object send(RpcRequest request) {
        Socket socket = null;
        try {
            socket = newSocket();
            //获取输出流，将客户端需要调用的远程方法参数request发送给
            ObjectOutputStream outputStream = new ObjectOutputStream(socket.getOutputStream());
            outputStream.writeObject(request);
            outputStream.flush();
            //获取输入流，得到服务端的返回结果
            ObjectInputStream inputStream = new ObjectInputStream(socket.getInputStream());
            Object result = inputStream.readObject();
            inputStream.close();
            outputStream.close();
            return result;
        } catch (Exception e) {
            throw new RuntimeException("发起远程调用异常:", e);
        } finally {
            if (socket != null) {
                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
