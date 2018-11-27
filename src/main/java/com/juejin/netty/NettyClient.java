package com.juejin.netty;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringEncoder;

import java.nio.channels.Channel;
import java.util.Date;

/****************************************************
 *
 * @Description:   Netty实现客户端
 *
 * @Author: DONGWENQI
 * @Date: Created in 9:46 AM 2018/11/27
 * @Modified By:
 ****************************************************/
public class NettyClient {

    public static void main(String[] args) throws InterruptedException {

        Bootstrap bootstrap = new Bootstrap();
        NioEventLoopGroup nioEventLoop = new NioEventLoopGroup();

        bootstrap.group(nioEventLoop)
                .channel(NioSocketChannel.class)
                .handler(new ChannelInitializer<io.netty.channel.Channel>() {
                    @Override
                    protected void initChannel(io.netty.channel.Channel channel) throws Exception {
                        channel.pipeline().addLast(new StringEncoder());
                    }
                });
        io.netty.channel.Channel channel = bootstrap.connect("99.99.99.12",8000).channel();

        while (true) {
            channel.writeAndFlush(new Date() + "hello");
            Thread.sleep(2000);
        }
    }
}
