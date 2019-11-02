package com.laravelshao.learning.grpc;

import com.laravelshao.learning.grpc.proto.MyRequest;
import com.laravelshao.learning.grpc.proto.MyResponse;
import com.laravelshao.learning.grpc.proto.StudentRequest;
import com.laravelshao.learning.grpc.proto.StudentResponse;
import com.laravelshao.learning.grpc.proto.StudentServiceGrpc;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

import java.util.Iterator;

/**
 * gRPC 客户端
 *
 * @author qinghua.shao
 * @date 2019/11/2
 * @since 1.0.0
 */
public class GrpcClient {

    public static void main(String[] args) {
        ManagedChannel managedChannel = ManagedChannelBuilder
                .forAddress("localhost", 8080).usePlaintext(true).build();
        StudentServiceGrpc.StudentServiceBlockingStub blockingStub = StudentServiceGrpc.newBlockingStub(managedChannel);

        // 简单请求简单响应
        MyResponse myResponse = blockingStub
                .getRealNameByUsername(MyRequest.newBuilder().setUsername("zhangsan").build());
        System.out.println(myResponse.getRealname());
        System.out.println("----------------------");

        // 简单请求流式响应
        Iterator<StudentResponse> iterator = blockingStub
                .getStudentsByAge(StudentRequest.newBuilder().setAge(20).build());
        while (iterator.hasNext()) {
            StudentResponse studentResponse = iterator.next();
            System.out.println(studentResponse.getName() + ", " + studentResponse.getAge() + ", " + studentResponse.getCity());
        }
        System.out.println("----------------------");



    }
}
