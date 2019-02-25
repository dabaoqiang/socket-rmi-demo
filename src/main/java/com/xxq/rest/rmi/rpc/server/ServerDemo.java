package com.xxq.rest.rmi.rpc.server;

/**
 * @author xiaoqiang
 * @Title: ServerDemo
 * @ProjectName socket-rmi-demo
 * @Description: TODO
 * @date 2019-02-26 00:08
 */
public class ServerDemo {

    public static void main(String[] args) {
        // 注册服务
        IGpHello iGpHello = new GpHelloImpl();
        RpcServer rpcServer = new RpcServer();
        rpcServer.publisher(iGpHello, 8888);
    }

}
