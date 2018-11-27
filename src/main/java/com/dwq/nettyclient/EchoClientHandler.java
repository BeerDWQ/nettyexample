package com.dwq.nettyclient;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.CharsetUtil;

/****************************************************
 *
 * @Description:  客户端代码
 *
 * @Author: DONGWENQI
 * @Date: Created in 10:43 AM 2018/11/8
 * @Modified By:
 ****************************************************/
public class EchoClientHandler extends SimpleChannelInboundHandler<ByteBuf>{

    //被通知Channel是活跃的时候，发送一条信息
    public void channelActive(ChannelHandlerContext ctx) {
        ctx.writeAndFlush(Unpooled.copiedBuffer("Netty rocks!", CharsetUtil.UTF_8));
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, ByteBuf msg) throws Exception {
        //记录已接收信息的转储
        System.out.println("Client recevied:" + msg.toString(CharsetUtil.UTF_8));
    }

    //异常处理
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {

        cause.printStackTrace();
        //发生异常时关闭Channel
        ctx.close();
    }
}
