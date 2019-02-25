package com.xxq.rest.rmi.rpc.client;

import com.xxq.rest.rmi.rpc.server.IGpHello;

/**
 * @author xiaoqiang
 * @Title: ClientDemo
 * @ProjectName socket-rmi-demo
 * @Description: TODO
 * @date 2019-02-25 23:51
 */
public class ClientDemo {


    public static void main(String[] args) {
        RpcClientProxy rpcClientProxy = new RpcClientProxy();
        IGpHello hello = rpcClientProxy.clientProxy(IGpHello.class, "localhost", 8888);
        System.out.println(hello.sayHello("mic"));
    }


}
