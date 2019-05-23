package com.laravelshao.learning.rpc.rmi.server;

import com.laravelshao.learning.rpc.rmi.service.HelloService;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

/**
 * @author shaoqinghua
 * @date 2018/10/5
 * @description RMI服务端
 */
public class RmiServer {

    public static void main(String[] args) {
        try {
            HelloService helloService = new HelloServiceImpl();
            LocateRegistry.createRegistry(1099);
            Naming.rebind("rmi://127.0.0.1/hello", helloService);
            System.out.println("服务启动成功");
        } catch (RemoteException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }
}
