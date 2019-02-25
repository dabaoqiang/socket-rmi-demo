package com.xxq.rest.rmi.rpc.client;

import com.xxq.rest.rmi.rpc.center.RpcRequest;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 * @author xiaoqiang
 * @Title: TCPTransport
 * @ProjectName socket-rmi-demo
 * @Description: TODO
 * @date 2019-02-25 23:55
 */
public class TCPTransport {

    private String host;

    private int port;


    public TCPTransport(String host, int port) {
        this.host = host;
        this.port = port;
    }

    /**
     * 打开连接
     *
     * @return
     */
    private Socket newScoket() {
        System.out.println("客户端开始连接服务器端");
        Socket socket = null;
        try {
            socket = new Socket(host, port);
            return socket;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return  null;
    }

    /**
     * 发送请求
     */
    public Object send(RpcRequest request) {
        Socket socket = null;
        try {
            socket = newScoket();
            // 第一，写入RpcRequest
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
            // 已经在访问了
            objectOutputStream.writeObject(request);
            // 将流全部写入到这个连接
            objectOutputStream.flush();
            // 获取输入流，拿到结果
            ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());
            Object object = objectInputStream.readObject();
            objectOutputStream.close();
            objectInputStream.close();
            return object;
        } catch (Exception e) {
            e.getStackTrace();
        } finally {
            if (socket != null) {
                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return  null;
    }
}
