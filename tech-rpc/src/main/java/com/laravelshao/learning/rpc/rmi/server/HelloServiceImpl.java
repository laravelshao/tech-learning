package com.laravelshao.learning.rpc.rmi.server;

import com.laravelshao.learning.rpc.rmi.service.HelloService;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 * @author shaoqinghua
 * @date 2018/10/5
 * @description 服务接口实现类需要继承UnicastRemoteObject
 */
public class HelloServiceImpl extends UnicastRemoteObject implements HelloService {

    protected HelloServiceImpl() throws RemoteException {
    }

    @Override
    public String sayHello(String msg) throws RemoteException {
        return "Hello " + msg;
    }
}
