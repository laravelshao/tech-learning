package com.laravelshao.learning.grpc;

import com.laravelshao.learning.grpc.service.StudentServiceImpl;
import io.grpc.Server;
import io.grpc.ServerBuilder;

import java.io.IOException;

/**
 * gRPC 服务端
 *
 * @author qinghua.shao
 * @date 2019/11/2
 * @since 1.0.0
 */
public class GrpcServer {

    private Server server;

    public static void main(String[] args) throws IOException, InterruptedException {

        GrpcServer grpcServer = new GrpcServer();

        grpcServer.start(); // 启动
        grpcServer.awaitTermination(); // 阻塞
    }

    private void start() throws IOException {
        this.server = ServerBuilder.forPort(8080).addService(new StudentServiceImpl()).build().start();
        System.out.println("server start ......");

        // 非阻塞
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            System.out.println("关闭JVM");
            this.server.shutdown();
        }));

        System.out.println("继续执行 ......");
    }

    private void stop() {
        if (null != this.server) {
            this.server.shutdown();
        }
    }

    private void awaitTermination() throws InterruptedException {
        if (null != this.server) {
            this.server.awaitTermination();
        }
    }

}
