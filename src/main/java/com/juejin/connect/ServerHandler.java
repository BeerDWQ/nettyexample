package com.juejin.connect;

import com.juejin.protocol.Packet;
import com.juejin.protocol.PacketCodeC;
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
 * @Date: Created in 11:04 AM 2018/11/29
 * @Modified By:
 ****************************************************/
public class ServerHandler extends ChannelInboundHandlerAdapter{

    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {

        ByteBuf byteBuf = (ByteBuf)msg;

        System.out.println(new Date() + "写入数据：" + byteBuf.toString(Charset.forName("utf-8")));

        System.out.println(new Date() + "开始写出数据：" );

        ByteBuf out = getByte(ctx);

        ctx.channel().writeAndFlush(out);
    }

    public ByteBuf getByte(ChannelHandlerContext ctx) {

        byte[] bytes = "return message".getBytes(Charset.forName("utf-8"));

        ByteBuf byteBuf = ctx.alloc().buffer();

        byteBuf.writeBytes(bytes);

        return byteBuf;
    }

}
