package com.laravelshao.learning.zookeeper.rmi.rpc.server;


import com.laravelshao.learning.zookeeper.rmi.rpc.service.RpcRequest;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.Socket;
import java.util.Map;

/**
 * @author shaoqinghua
 * @date 2018/10/5
 * @description
 */
public class ProcessorHandler implements Runnable {

    private Socket socket;

    Map<String, Object> handlerMap;

    public ProcessorHandler(Socket socket, Map<String, Object> handlerMap) {
        this.socket = socket;
        this.handlerMap = handlerMap;
    }

    @Override
    public void run() {
        ObjectInputStream inputStream = null;
        try {
            // 获取客户端的输入流
            inputStream = new ObjectInputStream(socket.getInputStream());
            // 反序列化远程传输的对象RpcRequest
            RpcRequest request = (RpcRequest) inputStream.readObject();
            // 反射调用本地方法
            Object result = invoke(request);
            // 使用输出流将结果输出给客户端
            ObjectOutputStream outputStream = new ObjectOutputStream(socket.getOutputStream());
            outputStream.writeObject(result);
            outputStream.flush();
            outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 通过反射调用目标服务方法
     *
     * @param request
     * @return
     * @throws NoSuchMethodException
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     */
    private Object invoke(RpcRequest request) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Object[] args = request.getParameters();
        Class<?>[] types = new Class[args.length];
        for (int i = 0; i < args.length; i++) {
            types[i] = args[i].getClass();
        }
        // 从handlerMap中根据服务名称获取具体的服务
        String serviceName = request.getClassName();
        String version = request.getVersion();
        if (version != null && !version.equals("")) {
            serviceName = serviceName + "-" + version;
        }
        Object service = handlerMap.get(serviceName);
        Method method = service.getClass().getMethod(request.getMethodName(), types);
        return method.invoke(service, args);
    }
}
