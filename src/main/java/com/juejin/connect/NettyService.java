package com.juejin.connect;

import io.netty.bootstrap.Bootstrap;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

/****************************************************
 *
 * @Description:   双端通信客户端
 *
 * @Author: DONGWENQI
 * @Date: Created in 10:39 AM 2018/11/29
 * @Modified By:
 ****************************************************/
public class NettyService {

    public static void main(String[] args) {

        NioEventLoopGroup bossGroup = new NioEventLoopGroup();

        NioEventLoopGroup workerGroup = new NioEventLoopGroup();

        ServerBootstrap bootstrap = new ServerBootstrap();

        bootstrap.group(bossGroup,workerGroup)
                .channel(NioServerSocketChannel.class)
                .childHandler(new ChannelInitializer<NioSocketChannel>() {
                    @Override
                    protected void initChannel(NioSocketChannel nioSocketChannel) throws Exception {

                    }

                });

        bootstrap.bind().addListener(future -> {
            if (future.isSuccess()) {

            }else {

            }
        });
    }

}
