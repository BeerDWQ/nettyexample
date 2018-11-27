package com.juejin;

import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;

/****************************************************
 *
 * @Description:
 *
 * @Author: DONGWENQI
 * @Date: Created in 7:25 PM 2018/11/25
 * @Modified By:
 ****************************************************/
public class NIOServer {

    public static void main(String[] args) throws Exception{
        Selector serverSelector = Selector.open();
        Selector clientSelector = Selector.open();

        new Thread(() -> {
            try {
                //对应IO编程中服务器启动
                ServerSocketChannel listenChannel = ServerSocketChannel.open();
                listenChannel.socket().bind(new InetSocketAddress(8000));
                listenChannel.configureBlocking(false);
                listenChannel.register(serverSelector, SelectionKey.OP_ACCEPT);
            }catch (Exception e) {

            }
        }).start();
    }
}
