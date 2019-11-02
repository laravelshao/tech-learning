package com.laravelshao.learning.grpc.service;

import com.laravelshao.learning.grpc.proto.MyRequest;
import com.laravelshao.learning.grpc.proto.MyResponse;
import com.laravelshao.learning.grpc.proto.StudentRequest;
import com.laravelshao.learning.grpc.proto.StudentResponse;
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

    /**
     * 简单请求简单响应
     *
     * @param request
     * @param responseObserver
     */
    @Override
    public void getRealNameByUsername(MyRequest request, StreamObserver<MyResponse> responseObserver) {
        System.out.println("接收到客户端消息: " + request.getUsername());

        responseObserver.onNext(MyResponse.newBuilder().setRealname("张三").build());
        responseObserver.onCompleted();
    }

    /**
     * 简单请求流式响应
     *
     * @param request
     * @param responseObserver
     */
    @Override
    public void getStudentsByAge(StudentRequest request, StreamObserver<StudentResponse> responseObserver) {
        System.out.println("接收到客户端消息: " + request.getAge());

        responseObserver.onNext(StudentResponse.newBuilder().setName("张三").setAge(20).setCity("上海").build());
        responseObserver.onNext(StudentResponse.newBuilder().setName("李四").setAge(30).setCity("北京").build());
        responseObserver.onNext(StudentResponse.newBuilder().setName("王五").setAge(40).setCity("成都").build());
        responseObserver.onNext(StudentResponse.newBuilder().setName("赵六").setAge(50).setCity("盐城").build());

        responseObserver.onCompleted();
    }
}
