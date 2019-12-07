package com.laravelshao.learning.io.zerocopy;

import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.Socket;

/**
 * 模拟旧的IO操作过程(客户端)
 * <p>
 * 测试文件 => jdk-8u212-macosx-x64.dmg
 * 结果 => 发送总字节数：264502407，耗时：261
 *
 * @author qinghua.shao
 * @date 2019/12/7
 * @since 1.0.0
 */
public class OldIOClient {

    public static void main(String[] args) throws IOException {

        Socket socket = new Socket("localhost", 8899);

        // 输入
        String fileName = "/Users/sylvia/Desktop/jdk-8u212-macosx-x64.dmg";
        FileInputStream inputStream = new FileInputStream(fileName);

        // 输出
        DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());
        byte[] bytes = new byte[4096];
        int len = 0;
        int totalCount = 0;
        long start = System.currentTimeMillis();
        while ((len = inputStream.read(bytes)) != -1) {
            dataOutputStream.write(bytes, 0, len);
            totalCount += len;
        }

        System.out.println("发送总字节数：" + totalCount + "，耗时：" + (System.currentTimeMillis() - start));

        dataOutputStream.close();
        socket.close();
        inputStream.close();
    }
}
