package com.juejin.protocol;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/****************************************************
 *
 * @Description:  客户端处理
 *
 * @Author: DONGWENQI
 * @Date: Created in 4:48 PM 2018/12/5
 * @Modified By:
 ****************************************************/
public class ClientHandler extends ChannelInboundHandlerAdapter{

    //登录发送数据包
    public void channelActive(ChannelHandlerContext ctx) {

        System.out.println("登录开始..........");
        LoginRequestPacket loginRequestPacket = new LoginRequestPacket();
        loginRequestPacket.setUsername("");
        loginRequestPacket.setPassword("");
        loginRequestPacket.setUserId(1);

        ByteBuf byteBuf = PacketCodeC.INSTANCE.encode(ctx.alloc(),loginRequestPacket);

        ctx.writeAndFlush(byteBuf);
    }

    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf byteBuf = (ByteBuf)msg;

        Packet packet = PacketCodeC.INSTANCE.decode(byteBuf);

        if(packet instanceof LoginResponsePacket) {

            LoginResponsePacket loginResponsePacket = (LoginResponsePacket)packet;

            if (loginResponsePacket.isSuccess()) {
                System.out.println("Login successful");
                //防止重复登录，设置channel属性
                LoginUtil.markLogin(ctx.channel());
            }else {
                System.out.println("Login fail:" + loginResponsePacket.getReason());
            }
        }else if (packet instanceof MessageResponsePacket) {

            MessageResponsePacket messageResponsePacket = (MessageResponsePacket) packet;

            System.out.println(messageResponsePacket.getMessage());
        }
    }
}
