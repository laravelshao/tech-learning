# gRPC 异常处理


### 利用 `protobuf-maven-plugin` 插件编译未生成gRPC文件

需要单独执行 protobuf:compile-custom 生成gRPC文件

 - protobuf:compile: 生成 protobuf 序列化相关文件(数据交换时的 Java Bean)
 - protobuf:compile-custom: 生成 grpc 相关的文件(用于与服务端通信)


### 指定Java Bean文件及gRPC文件生成目录

####  Maven 方式



#### Gradle 方式

参考 [protobuf-gradle-plugin](https://github.com/google/protobuf-gradle-plugin) 文档

1.指定 Java Bean 文件生成目录

By default generated Java files are under $generatedFilesBaseDir/$sourceSet/$builtinPluginName, 
where $generatedFilesBaseDir is $buildDir/generated/source/proto by default, and is configurable. E.g.,

```
protobuf {
  ...
  generatedFilesBaseDir = "src"
}
```

2.指定 gRPC 文件生成目录

The subdirectory name, which is by default $builtinPluginName, can also be changed by setting 
the outputSubDir property in the builtins or plugins block of a task configuration 
within generateProtoTasks block (see previous section). E.g.,

```
{ task ->
  task.plugins {
    grpc {
      // Write the generated files under
      // "$generatedFilesBaseDir/$sourceSet/grpcjava"
      outputSubDir = 'java'
    }
  }
}
```



