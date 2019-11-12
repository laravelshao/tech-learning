# gRPC 异常处理


### 1.利用 `protobuf-maven-plugin` 插件编译未生成gRPC文件

需要单独执行 protobuf:compile-custom 生成gRPC文件

 - protobuf:compile: 生成 protobuf 序列化相关文件(数据交换时的 Java Bean)
 - protobuf:compile-custom: 生成 grpc 相关的文件(用于与服务端通信)


### 2.指定Java Bean文件及gRPC文件生成目录

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

### 3. node npm 安装 grpc-tools 失败处理

参考[Node gRPC 静态代码生成](https://github.com/grpc/grpc/tree/master/examples/node/static_codegen)

执行命令 `npm install -g grpc-tools` 或 `sudo npm install -g grpc-tools` 都报以下错误

```
LaravelShaodeMacBook-Pro:~ root# npm install -g grpc-tools
/usr/local/bin/grpc_tools_node_protoc -> /usr/local/lib/node_modules/grpc-tools/bin/protoc.js
/usr/local/bin/grpc_tools_node_protoc_plugin -> /usr/local/lib/node_modules/grpc-tools/bin/protoc_plugin.js

> grpc-tools@1.8.0 install /usr/local/lib/node_modules/grpc-tools
> node-pre-gyp install

node-pre-gyp WARN Using needle for node-pre-gyp https download 
node-pre-gyp ERR! Completion callback never invoked! 
node-pre-gyp ERR! System Darwin 19.0.0
node-pre-gyp ERR! command "/usr/local/bin/node" "/usr/local/lib/node_modules/grpc-tools/node_modules/.bin/node-pre-gyp" "install"
node-pre-gyp ERR! cwd /usr/local/lib/node_modules/grpc-tools
node-pre-gyp ERR! node -v v13.0.1
node-pre-gyp ERR! node-pre-gyp -v v0.12.0
node-pre-gyp ERR! This is a bug in `node-pre-gyp`.
node-pre-gyp ERR! Try to update node-pre-gyp and file an issue if it does not help:
node-pre-gyp ERR!     <https://github.com/mapbox/node-pre-gyp/issues>
npm ERR! code ELIFECYCLE
npm ERR! errno 6
npm ERR! grpc-tools@1.8.0 install: `node-pre-gyp install`
npm ERR! Exit status 6
npm ERR! 
npm ERR! Failed at the grpc-tools@1.8.0 install script.
npm ERR! This is probably not a problem with npm. There is likely additional logging output above.

npm ERR! A complete log of this run can be found in:
npm ERR!     /var/root/.npm/_logs/2019-11-12T13_25_38_239Z-debug.log

```

解决方法：添加参数 --unsafe-perm=true，成功安装([解决npm安装失败](https://github.com/ios-control/ios-deploy/issues/188))

```
npm install -g grpc-tools --unsafe-perm=true
```
