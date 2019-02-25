package com.xxq.rest.rmi.rpc.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author xiaoqiang
 * @Title: RpcServer
 * @ProjectName socket-rmi-demo
 * @Description: TODO
 * @date 2019-02-25 23:35
 */
public class RpcServer {

    private static final ExecutorService executorService = Executors.newCachedThreadPool();

    public void publisher(final Object service, int port) {
        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(port);
            while (true) {
                Socket socket = serverSocket.accept();
                System.out.println("测试：往ObjectOutputStream输入是否会打印东西出来");
                executorService.execute(new ProcessorHandler(socket, service));
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (serverSocket != null) {
                try {
                    serverSocket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }


            }
        }

    }
}
