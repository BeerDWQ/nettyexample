package com.juejin.client;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.util.Date;
import java.util.concurrent.TimeUnit;

/****************************************************
 *
 * @Description:   客户端启动流程
 *
 * @Author: DONGWENQI
 * @Date: Created in 5:01 PM 2018/11/28
 * @Modified By:
 ****************************************************/
public class ClientServer {

    private final int MAX_RETRY=5;

    public static void main(String[] args) {
        //声明线程组
        NioEventLoopGroup workGroup = new NioEventLoopGroup();
        //声明启动端
        Bootstrap bootstrap = new Bootstrap();
        //常规初始化客户端
        bootstrap.group(workGroup)
                .channel(NioSocketChannel.class)
                .handler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel socketChannel) throws Exception {

                    }
                });
        //创建连接
        bootstrap.connect("99.99.99.12",80).addListener(future -> {
            if (future.isSuccess()) {
                System.out.println("连接成功");
            }else {
                System.out.println("连接失败");
            }
        });
    }

    public void connect(Bootstrap bootstrap, String host, int port) {
        bootstrap.connect(host,port).addListener(future -> {
            if (future.isSuccess()) {
                System.out.println("连接成功");
            }else {
                System.out.println("连接失败，开始重连");
                bootstrap.connect(host,port);
            }
        });
    }

    //带重连时间间隔
    public void connectNew(Bootstrap bootstrap, String host, int port, int retry) {
        bootstrap.connect(host,port).addListener(future -> {
           if (future.isSuccess()) {
               System.out.println("连接成功");
           }else if (retry == 0){
               System.out.println("重连次数用完");
           }else {
               //当前第几次连接
               int order = (MAX_RETRY-retry)+1;
               //重新连接间隔
               int delay = 1 << order;
               //重新连接
               System.out.println(new Date() + ": 连接失败，第" + order + "次重连……");
               bootstrap.config().group().schedule(()->connectNew(bootstrap,host,port,retry-1),delay, TimeUnit.SECONDS);
           }
        });
    }
}
