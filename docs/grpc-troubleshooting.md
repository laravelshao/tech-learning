# gRPC 异常处理


#### 利用 `protobuf-maven-plugin` 插件编译未生成gRPC文件

需要单独执行 protobuf:compile-custom 生成gRPC文件

 - protobuf:compile: 生成 protobuf 序列化相关文件(数据交换时的 Java Bean)
 - protobuf:compile-custom: 生成 grpc 相关的文件(用于与服务端通信)


