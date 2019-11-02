package com.laravelshao.learning.grpc;

import com.laravelshao.learning.grpc.proto.MyRequest;
import com.laravelshao.learning.grpc.proto.MyResponse;
import com.laravelshao.learning.grpc.proto.StudentRequest;
import com.laravelshao.learning.grpc.proto.StudentResponse;
import com.laravelshao.learning.grpc.proto.StudentResponseList;
import com.laravelshao.learning.grpc.proto.StudentServiceGrpc;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.stub.StreamObserver;

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
        // 异步
        StudentServiceGrpc.StudentServiceStub serviceStub = StudentServiceGrpc.newStub(managedChannel);

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

        // 流式请求简单响应(流式请求必须是异步)
        final StreamObserver<StudentResponseList> studentResponseListStreamObserver = new StreamObserver<StudentResponseList>() {
            @Override
            public void onNext(StudentResponseList studentResponseList) {
                studentResponseList.getStudentResponseList().forEach(StudentResponse -> {
                            System.out.println(StudentResponse.getName() + ", " + StudentResponse.getAge() + ", " + StudentResponse.getCity());
                            System.out.println("************************");
                        }
                );
            }

            @Override
            public void onError(Throwable throwable) {
                System.out.println(throwable.getMessage());
            }

            @Override
            public void onCompleted() {
                System.out.println("completed ......");
            }
        };

        StreamObserver<StudentRequest> studentRequestStreamObserver = serviceStub.getStudentsWrapperByAges(studentResponseListStreamObserver);

        studentRequestStreamObserver.onNext(StudentRequest.newBuilder().setAge(20).build());
        studentRequestStreamObserver.onNext(StudentRequest.newBuilder().setAge(30).build());
        studentRequestStreamObserver.onNext(StudentRequest.newBuilder().setAge(40).build());
        studentRequestStreamObserver.onNext(StudentRequest.newBuilder().setAge(50).build());

        studentRequestStreamObserver.onCompleted();

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
