package com.xxq.rest.rmi.rpc.client;

import java.lang.reflect.Proxy;

/**
 * @author xiaoqiang
 * @Title: RpcClientProxy
 * @ProjectName socket-rmi-demo
 * @Description: TODO
 * @date 2019-02-25 23:52
 */
public class RpcClientProxy {


    /**
     * 创建客户端的远程代理。通过远程代理进行访问
     *
     * @param interfaceCls
     * @param host
     * @param port
     * @param <T>
     * @return
     */
    public <T> T clientProxy(final Class<T>
                                     interfaceCls,
                             final String host, final int port) {
        return (T) Proxy.newProxyInstance(interfaceCls.getClassLoader(),
                new Class[]{interfaceCls}, new RemoteInvocationHandler(host, port));
    }

}
