package com.xxq.rest.rmi.rpc.server;

/**
 * @author xiaoqiang
 * @Title: GpHelloImpl
 * @ProjectName socket-rmi-demo
 * @Description: TODO
 * @date 2019-02-25 23:31
 */
public class GpHelloImpl implements IGpHello {
    @Override
    public String sayHello(String msg) {
        return "Hello , " + msg;
    }
}
