package com.juejin.superclient.client;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandler;
import io.netty.channel.ChannelInboundHandlerAdapter;

/****************************************************
 *
 * @Description:  自定义handler
 *
 * @Author: DONGWENQI
 * @Date: Created in 11:21 AM 2018/12/10
 * @Modified By:
 ****************************************************/
public class LifeCycleTestHandler extends ChannelInboundHandlerAdapter{

    public void handlerAdded(ChannelHandlerContext ctx) throws Exception{
        System.out.println("逻辑处理器被添加：handler added");
        super.handlerAdded(ctx);
    }

    public void channelRegisted(ChannelHandlerContext ctx) throws Exception {
        System.out.println("channel 绑定到线程(NioEventLoop): channelRegistered");
        super.channelRegistered(ctx);
    }

    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("channel 准备就绪： channelActive()");
        super.channelActive(ctx);
    }

    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception{
        System.out.println("channel 有数据可读：channelRead()");
        super.channelRead(ctx,msg);
    }

    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception{
        System.out.println("channel 某次数据读取完：channelReadComplete()");
        super.channelReadComplete(ctx);
    }

    public void channelInActive(ChannelHandlerContext ctx) throws Exception{
        System.out.println("channel 被关闭：channelInActive()");
        super.channelInactive(ctx);
    }

    public void channelUnregistered(ChannelHandlerContext ctx) throws Exception{
        System.out.println("channel 取消线程(NioEventLoop) 的绑定：channelUnregistered()");
        super.channelUnregistered(ctx);
    }

    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        System.out.println("逻辑处理器被移除：handlerRemoved()");
        super.handlerRemoved(ctx);
    }
}
