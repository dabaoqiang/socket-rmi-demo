package com.xxq.rest.rmi.rpc.client;

import com.xxq.rest.rmi.rpc.center.RpcRequest;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @author xiaoqiang
 * @Title: RemoteInvocationHandler
 * @ProjectName socket-rmi-demo
 * @Description: TODO
 * @date 2019-02-25 23:52
 */
public class RemoteInvocationHandler implements InvocationHandler {
    /**
     * 服务器的ip
     */
    private String host;
    /**
     * 服务器的端口
     */
    private int port;

    public RemoteInvocationHandler(String host, int port) {
        this.host = host;
        this.port = port;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        // 第一， RecRequest，服务端也有，可以反向编译
        RpcRequest request = new RpcRequest();
        request.setClassName(method.getDeclaringClass().getName());
        request.setMethodName(method.getName());
        request.setParameters(args);
        // 第二，通过TCP协议发送到服务器端Socket
        //通过tcp传输协议进行传输
        TCPTransport tcpTransport = new TCPTransport(this.host, this.port);
        return tcpTransport.send(request);
    }
}
