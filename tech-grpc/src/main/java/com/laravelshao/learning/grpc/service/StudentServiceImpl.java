package com.laravelshao.learning.grpc.service;

import com.laravelshao.learning.grpc.proto.MyRequest;
import com.laravelshao.learning.grpc.proto.MyResponse;
import com.laravelshao.learning.grpc.proto.StudentServiceGrpc;
import io.grpc.stub.StreamObserver;

/**
 * 服务实现 继承 gRPC 生成的抽象类
 *
 * @author qinghua.shao
 * @date 2019/11/2
 * @since 1.0.0
 */
public class StudentServiceImpl extends StudentServiceGrpc.StudentServiceImplBase {

    @Override
    public void getRealNameByUsername(MyRequest request, StreamObserver<MyResponse> responseObserver) {
        System.out.println("接收到客户端消息: " + request.getUsername());

        responseObserver.onNext(MyResponse.newBuilder().setRealname("张三").build());
        responseObserver.onCompleted();
    }
}
