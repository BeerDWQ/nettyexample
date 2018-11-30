package com.juejin.connect;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import java.nio.charset.Charset;
import java.util.Date;

/****************************************************
 *
 * @Description:
 *
 * @Author: DONGWENQI
 * @Date: Created in 10:23 AM 2018/11/29
 * @Modified By:
 ****************************************************/
public class FirstClientHandler extends ChannelInboundHandlerAdapter{

    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println(new Date()+"开始写入数据");
        //获取数据
        ByteBuf byteBuf = getByteBuf(ctx);
        //写入数据
        ctx.channel().writeAndFlush(byteBuf);
    }

    private ByteBuf getByteBuf(ChannelHandlerContext ctx) {
        //获取二进制字符流
        ByteBuf byebuffer = ctx.alloc().buffer();
        //转码
        byte[] bytes = "hello netty".getBytes(Charset.forName("utf-8"));
        //写入字符流
        byebuffer.writeBytes(bytes);
        //返回字符流
        return byebuffer;
    }

    public void channelRead(ChannelHandlerContext ctx, Object msg) {

        ByteBuf byteBuf = (ByteBuf) msg;

        System.out.println(new Date() + byteBuf.toString(Charset.forName("utf-8")));
    }
}
