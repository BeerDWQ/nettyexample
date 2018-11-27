package com.rpckid.server;

import com.rpckid.common.*;
import com.sun.xml.internal.ws.api.handler.MessageHandler;
import io.netty.bootstrap.Bootstrap;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.timeout.ReadTimeoutHandler;

/****************************************************
 *
 * @Description:   服务器-
 *
 * @Author: DONGWENQI
 * @Date: Created in 5:31 PM 2018/11/22
 * @Modified By:
 ****************************************************/
public class RPCServer {

    private String ip;
    private int port;
    private int ioThread;
    private int workerThread;

    private MessageHandlers handlers = new MessageHandlers();
    private MessageRegistry registry = new MessageRegistry();

    {
        handlers.defaultHandler(new DefaultHandler());
    }

    public RPCServer(String ip, int port, int ioThread, int workerThread) {
        this.ip = ip;
        this.port = port;
        this.ioThread = ioThread;
        this.workerThread = workerThread;
    }

    private ServerBootstrap bootstrap;
    private EventLoopGroup group;
    private MessageCollector messageCollector;
    private Channel serverChannel;

    public RPCServer service (String type, Class<?> reqClass, IMessageHandler<?> handler) {
            MessageRegistry.register(type, reqClass);
            MessageHandlers.register(type, handler);
            return this;
    }

    public void start() {
        bootstrap = new ServerBootstrap();
        group = new NioEventLoopGroup(ioThread);
        bootstrap.group(group);
        messageCollector = new MessageCollector(workerThread);
        MessageEncoder encoder = new MessageEncoder();
        bootstrap.channel(NioServerSocketChannel.class).childHandler(new ChannelInitializer<SocketChannel>() {

            @Override
            protected void initChannel(SocketChannel socketChannel) throws Exception {
                ChannelPipeline pip = socketChannel.pipeline();
                pip.addLast(new ReadTimeoutHandler(60));
                pip.addLast(new MessageDecoder());
                pip.addLast(encoder);
                pip.addLast(messageCollector);
            }
    });
        bootstrap.option(ChannelOption.SO_BACKLOG,100)
                .option(ChannelOption.SO_REUSEADDR,true)
                .option(ChannelOption.TCP_NODELAY,true)
                .option(ChannelOption.SO_KEEPALIVE,true);
        serverChannel = bootstrap.bind(this.ip,this.port).channel();
        System.out.printf("server started @ %s:%d\n", ip, port);
    }

    public void stop() {
        //关闭套接字服务
        serverChannel.close();
        //关闭线程池
        group.shutdownGracefully();
        //关闭业务线程
        messageCollector.closeGracefully();
    }
}
