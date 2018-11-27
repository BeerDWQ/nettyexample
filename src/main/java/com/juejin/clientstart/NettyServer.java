package com.juejin.clientstart;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.util.concurrent.Future;
import io.netty.util.concurrent.GenericFutureListener;

/****************************************************
 *
 * @Description:   客户端启动流程分析
 *
 * @Author: DONGWENQI
 * @Date: Created in 10:55 AM 2018/11/27
 * @Modified By:
 ****************************************************/
public class NettyServer {
    public static void main(String[] args) {
        //监听请求的线程组
        NioEventLoopGroup bossGroup = new NioEventLoopGroup();
        //处理请求的线程组
        NioEventLoopGroup workerGroup = new NioEventLoopGroup();

        //创建服务端的启动引导
        ServerBootstrap serverBootstrap = new ServerBootstrap();

        serverBootstrap
                .group(bossGroup, workerGroup)//引入两个线程组
                .channel(NioServerSocketChannel.class)//指定IO模型
                .childHandler(new ChannelInitializer<NioSocketChannel>() {//处理逻辑
                    protected void initChannel(NioSocketChannel ch) {
                    }
                });
        //异步的绑定端口事件
        serverBootstrap.bind(8000).addListener(new GenericFutureListener<Future<? super Void>>() {
            @Override
            public void operationComplete(Future<? super Void> future) throws Exception {
                if (future.isSuccess()) {
                    System.out.println("端口绑定成功");
                }else {
                    System.out.println("端口绑定失败");
                }
            }
        });
    }

    //端口绑定方法(端口占用后向上选择端口fiq)
    private static void bind(final ServerBootstrap serverBootstrap, final int port) {
        serverBootstrap.bind(port).addListener(new GenericFutureListener<Future<? super Void>>() {
            @Override
            public void operationComplete(Future<? super Void> future) throws Exception {
                if (future.isSuccess()) {
                    System.out.println("端口绑定成功");
                }else {
                    System.out.println("端口绑定失败");
                    bind(serverBootstrap,port+1);
                }
            }
        });
    }
}


