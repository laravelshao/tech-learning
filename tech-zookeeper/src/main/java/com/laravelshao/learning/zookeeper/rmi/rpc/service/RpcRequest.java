package com.laravelshao.learning.zookeeper.rmi.rpc.service;

import java.io.Serializable;

/**
 * @author shaoqinghua
 * @date 2018/10/5
 * @description
 */
public class RpcRequest implements Serializable {

    private static final long serialVersionUID = -8432230831201203963L;

    private String className;
    private String methodName;
    private Object[] parameters;
    private String version;

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public Object[] getParameters() {
        return parameters;
    }

    public void setParameters(Object[] parameters) {
        this.parameters = parameters;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }
}
