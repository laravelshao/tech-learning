package com.laravelshao.learning.rpc.rmi.service;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * @author shaoqinghua
 * @date 2018/10/5
 * @description 自定义服务接口必须继承Remote接口
 */
public interface HelloService extends Remote {

    String sayHello(String msg) throws RemoteException;

}
