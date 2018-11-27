package com.juejin;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

/****************************************************
 *
 * @Description:
 *
 * @Author: DONGWENQI
 * @Date: Created in 9:43 AM 2018/11/25
 * @Modified By:
 ****************************************************/
public class IOServer {

    public static void main(String[] args) throws Exception {

        ServerSocket serverSocket = new ServerSocket(8000);

        // (1) 接收新连接线程
        new Thread(() -> {
            try {
                // (1) 阻塞方法获取新的连接
                Socket socket = serverSocket.accept();

                // (2) 每一个新的连接都创建一个线程，负责读取数据
                new Thread(() -> {
                    try {
                        int len;
                        byte[] data = new byte[1024];
                        InputStream inputStream = socket.getInputStream();
                        // (3) 按字节流方式读取数据
                        while ((len = inputStream.read(data)) != -1) {
                            System.out.println(new String(data, 0, len));
                        }
                    } catch (IOException e) {
                    }
                }).start();

            } catch (IOException e) {
            }


        }).start();
    }
}
