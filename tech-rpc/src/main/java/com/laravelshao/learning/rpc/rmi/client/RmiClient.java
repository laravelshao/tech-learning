package com.laravelshao.learning.rpc.rmi.client;

import com.laravelshao.learning.rpc.rmi.service.HelloService;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

/**
 * @author shaoqinghua
 * @date 2018/10/5
 * @description RMI客户端
 */
public class RmiClient {

    public static void main(String[] args) throws RemoteException, NotBoundException, MalformedURLException {
        HelloService helloService = (HelloService) Naming.lookup("rmi://127.0.0.1/hello");
        System.out.println(helloService.sayHello("mina"));
    }
}
