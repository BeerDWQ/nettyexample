package com.dwq.nettyserver;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.CharsetUtil;

import java.nio.ByteBuffer;

/****************************************************
 *
 * @Description:  响应处理
 *
 * @Author: DONGWENQI
 * @Date: Created in 3:49 PM 2018/11/7
 * @Modified By:
 ****************************************************/
public class EchoServerHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf in = (ByteBuf) msg;
        //输出数据到控制台
        System.out.println("Servrer received:" + in.toString(CharsetUtil.UTF_8));

        //将接受到的消息写给发送者，不冲刷出消息站
        ctx.write(in);
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        //将未决消息冲刷到远程节点，并且关闭该Channel
        ctx.writeAndFlush(Unpooled.EMPTY_BUFFER)
                .addListener(ChannelFutureListener.CLOSE);
        super.channelReadComplete(ctx);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        //打印异常栈跟踪
        cause.printStackTrace();
        ctx.close();
    }
}
