package com.juejin;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.util.Iterator;
import java.util.Set;

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

                while(true) {
                    //检测是否有新的连接
                    if(serverSelector.select(1) > 0) {
                        Set<SelectionKey> set = serverSelector.selectedKeys();
                        Iterator<SelectionKey> keys = set.iterator();

                        while(keys.hasNext()) {
                            SelectionKey key = keys.next();
                            if (key.isAcceptable()) {
                                try {
                                    //新来一个连接，注册到channelSelector中
                                    SocketChannel clientChannel = ((ServerSocketChannel)key.channel()).accept();
                                    clientChannel.configureBlocking(false);
                                    clientChannel.register(clientSelector,key.OP_READ);
                                } finally {
                                    keys.remove();
                                }
                            }
                        }
                    }
                }
            }catch (Exception e) {

            }
        }).start();

        new Thread(() -> {
            try {
                while (true) {
                    if(clientSelector.select(1) > 0) {
                        Set<SelectionKey> set = clientSelector.selectedKeys();
                        Iterator<SelectionKey> keys = set.iterator();

                        while (keys.hasNext()) {
                            SelectionKey key = keys.next();
                            if (key.isReadable()) {
                                try {
                                    SocketChannel clientChannel = (SocketChannel) key.channel();
                                    ByteBuffer buffer = ByteBuffer.allocate(1024);
                                    //面向buffer
                                    clientChannel.read(buffer);
                                    buffer.flip();
                                    System.out.println(Charset.defaultCharset().newDecoder().decode(buffer)
                                            .toString());
                                }finally {
                                    keys.remove();
                                    key.interestOps(SelectionKey.OP_READ);
                                }

                            }
                        }
                    }
                }
            } catch (Exception e) {

            }
        }).start();
    }
}
