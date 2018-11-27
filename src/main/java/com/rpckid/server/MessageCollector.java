package com.rpckid.server;

import com.rpckid.common.IMessageHandler;
import com.rpckid.common.MessageHandlers;
import com.rpckid.common.MessageInput;
import com.rpckid.common.MessageRegistry;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/****************************************************
 *
 * @Description:  服务器-组合模块
 *
 * @Author: DONGWENQI
 * @Date: Created in 3:46 PM 2018/11/22
 * @Modified By:
 ****************************************************/
public class MessageCollector extends ChannelInboundHandlerAdapter{
    //业务线程池
    private ThreadPoolExecutor executor;
    //
    private MessageHandlers messageHandlers;

    public MessageCollector(int workerThreads) {

        //设置线程数
        BlockingQueue<Runnable> queue = new ArrayBlockingQueue<>(1000);

        //
        ThreadFactory factory = new ThreadFactory() {

            AtomicInteger seq = new AtomicInteger();

            @Override
            public Thread newThread(Runnable r) {
                Thread t = new Thread(r);
                t.setName("rpc-"+seq.incrementAndGet());
                return t;
            }
        };

        //闲置时间超过30秒的线程自动销毁
        this.executor = new ThreadPoolExecutor(1,workerThreads,30,TimeUnit.SECONDS,queue,factory,new ThreadPoolExecutor.CallerRunsPolicy());

    }
    public void closeGracefully() {

        this.executor.shutdown();

        try {
            this.executor.awaitTermination(10,TimeUnit.SECONDS);

        } catch (InterruptedException ex) {

        }
        this.executor.shutdownNow();
    }


    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("Connection comes");
        ctx.close();
    }

    public void channleRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        if(msg instanceof MessageInput) {
            // 用业务线程池处理消息
            this.executor.execute(() -> {
                this.handleMessage(ctx, (MessageInput)msg);
            });
        }
    }

    public void handleMessage(ChannelHandlerContext ctx, MessageInput msg) {
        //业务逻辑
        Class<?> clazz = MessageRegistry.get(msg.getType());
        if(clazz == null) {
            messageHandlers.defaultHandler().handle(ctx,msg.getId(),msg);
            return;
        }
        Object o = msg.getPayLoad(clazz);

        IMessageHandler<Object> handler = (IMessageHandler<Object>) MessageHandlers.get(msg.getType());
        if(handler != null) {
            handler.handle(ctx, msg.getId(),o);
        } else {
            messageHandlers.defaultHandler().handle(ctx,msg.getId(),msg);
        }
    }

    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        System.out.println("error");
        cause.printStackTrace();
        ctx.close();
    }


}
