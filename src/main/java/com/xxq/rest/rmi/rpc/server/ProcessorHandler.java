package com.xxq.rest.rmi.rpc.server;


import com.xxq.rest.rmi.rpc.center.RpcRequest;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.Socket;

/**
 * @author xiaoqiang
 * @Title: ProcessorHandler
 * @ProjectName socket-rmi-demo
 * @Description: TODO
 * @date 2019-02-25 23:38
 */
public class ProcessorHandler implements Runnable {
    /**
     * 听取客服端的socket请求
     */
    private Socket socket;
    /**
     * 服务端发布的服务
     */
    private Object service;

    public ProcessorHandler(Socket socket, Object service) {
        this.socket = socket;
        this.service = service;
    }

    @Override
    public void run() {
        // 第一，读取对象;
        ObjectInputStream objectInputStream = null;
        try {
            System.out.println("当前线程" + Thread.currentThread().getName() + "开始处理：-------------");
            objectInputStream = new ObjectInputStream(socket.getInputStream());
            // 拿到远程RecRequest
            RpcRequest rpcRequest = (RpcRequest) objectInputStream.readObject();
            // 拿到客户端的request请求，本地方法调用
            Object invoke = invoke(rpcRequest);
            // 返回结果给客户端
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
            objectOutputStream.writeObject(invoke);
            objectOutputStream.flush();
            System.out.println("当前线程完毕处理" + Thread.currentThread().getName() + "-------------");
            // 关闭流
            objectOutputStream.close();
            objectInputStream.close();
        } catch (Exception e) {
            e.getStackTrace();
        } finally {
            if (objectInputStream != null) {
                try {
                    objectInputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    private Object invoke(RpcRequest request) throws InvocationTargetException, IllegalAccessException, NoSuchMethodException {
        //一下均为反射操作，目的是通过反射调用服务
        Object[] args = request.getParameters();
        Class<?>[] types = new Class[args.length];
        for (int i = 0; i < args.length; i++) {
            types[i] = args[i].getClass();
        }
        Method method = service.getClass().getMethod(request.getMethodName(), types);
        return method.invoke(service, args);
    }

}
