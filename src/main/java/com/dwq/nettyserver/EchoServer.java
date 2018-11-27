package com.dwq.nettyserver;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

import java.net.InetSocketAddress;

/****************************************************
 *
 * @Description:  引导服务器
 *
 * @Author: DONGWENQI
 * @Date: Created in 4:20 PM 2018/11/7
 * @Modified By:
 ****************************************************/
public class EchoServer {

    private final int port;

    public EchoServer(int port) {
        this.port = port;
    }

    public static void main(String[] args) throws Exception{

        if (args.length != 1) {
            System.out.println("Usage: " + EchoServer.class.getSimpleName() + " ");
        }
        //定义服务端口(端口格式不正确抛出异常NumberFormatException)
        int port = Integer.parseInt(args[0]);
        //调用服务器的start方法
        new EchoServer(port).start();
    }

    public void start() throws Exception {

        final  EchoServerHandler serverHandler = new EchoServerHandler();
        //创建Event-LoopGroup
        EventLoopGroup group = new NioEventLoopGroup();
        try{
            //创建Server-BootStrap
            ServerBootstrap b = new ServerBootstrap();

            b.group(group)
                    .channel(NioServerSocketChannel.class)//指定使用的NIO传输Channel
                    .localAddress(new InetSocketAddress(port))//使用指定的端口设置套接字地址
                    .childHandler(new ChannelInitializer() {//添加一个ServerHandler到子Channel的ChannelPipeline

                        @Override
                        protected void initChannel(Channel ch) throws Exception {
                            ch.pipeline().addLast(serverHandler);
                        }
                    });
            //异步的绑定服务器;调用sync()方法阻塞等待直到完成
            ChannelFuture f = b.bind().sync();
            //获取Channel的CloseFuture，并且阻塞当前线程直到它完成
            f.channel().closeFuture().sync();
        }finally {
            //关闭EventLoopGroup，释放所有资源
            group.shutdownGracefully().sync();
        }
    }
}
