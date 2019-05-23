package com.laravelshao.learning.dubbo.consumer;

/**
 * @author shaoqinghua
 * @date 2018/7/7
 * @description 代理对象
 */
public class Proxy0 {

    /**
     * handler是JavassistProxyFactory.getProxy()传递 new InvokerInvocationHandler(invoker)
     */
    public String sayHello(String arg0) {
        //Object[] args = new Object[1];
        //args[0] = ($w) $1;
        //// handler->InvokerInvocationHandler
        //Object ret = handler.invoke(this, methods[0], args);
        //return (java.lang.String) ret;
        return null;
    }
}
