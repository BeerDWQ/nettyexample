package com.dwq.nettyclient;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoop;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.net.InetSocketAddress;

/****************************************************
 *
 * @Description:
 *
 * @Author: DONGWENQI
 * @Date: Created in 11:57 AM 2018/11/8
 * @Modified By:
 ****************************************************/
public class EchoClient {

    private final String host;
    private final int port;

    public EchoClient(String host, int port) {

        this.host = host;
        this.port = port;

    }

    public void start() throws Exception {

        EventLoopGroup group = new NioEventLoopGroup();
        try {
            //指定NioEventLoopGroup以处理客户端事件；需要适用NIO实现
            Bootstrap b = new Bootstrap();
            //适用于NIO传输的Channel类型
            b.group(group).channel(NioSocketChannel.class)
                    .remoteAddress(new InetSocketAddress(host,port))//设置服务器的InetSocketAddress
                    .handler(new ChannelInitializer<SocketChannel>() {//添加Echo-ClientHandler实例
                        public void initChannel(SocketChannel ch) throws Exception{
                           ch.pipeline().addLast(new EchoClientHandler());
                        }
                    });
            //连接到远程节点，阻塞等待直到连接完成
            ChannelFuture f = b.connect().sync();
            //阻塞直到Channel关闭
            f.channel().closeFuture().sync();
        }finally {
            //关闭线程池并释放所有资源
            group.shutdownGracefully().sync();
        }
    }

    public static void main(String[] args) throws Exception{

        if (args.length != 2) {
            System.out.println("Usage: " + EchoClient.class.getSimpleName()+"<host><port>");
            return;
        }

        String host = args[0];
        int port = Integer.parseInt(args[1]);
        new EchoClient(host,port).start();
    }
}
