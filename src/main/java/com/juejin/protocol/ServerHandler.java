package com.juejin.protocol;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.util.Date;

/****************************************************
 *
 * @Description:  登录处理
 *
 * @Author: DONGWENQI
 * @Date: Created in 3:51 PM 2018/12/5
 * @Modified By:
 ****************************************************/
public class ServerHandler extends ChannelInboundHandlerAdapter{

    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        //获取信息
        ByteBuf byteBuf = (ByteBuf)msg;

        //解析数据包
        Packet packet = PacketCodeC.INSTANCE.decode(byteBuf);

        //根据数据包类型进行操作
        if(packet instanceof LoginRequestPacket) {

            LoginRequestPacket loginRequestPacket = (LoginRequestPacket)packet;
            LoginResponsePacket loginResponsePacket = new LoginResponsePacket();

            loginResponsePacket.setVersion(packet.getVersion());
            //判断登录条件
            if(valid(loginRequestPacket)) {
                loginResponsePacket.setSuccess(true);
                System.out.println(new Date()+"Login successful");
            }else {
                loginResponsePacket.setSuccess(false);
                loginResponsePacket.setReason("Incorrect username or password");
                System.out.println(new Date() + "Login fail");
            }
            //编码响应数据包
            ByteBuf responseBuf=PacketCodeC.INSTANCE.encode(ctx.alloc(),loginResponsePacket);
            //写入响应数据包
            ctx.channel().writeAndFlush(responseBuf);
        }else if (packet instanceof MessageRequestPacket) {//处理消息数据包

            MessageRequestPacket messageRequestPacket = ((MessageRequestPacket) packet);
            MessageResponsePacket messageResponsePacket = new MessageResponsePacket();

            System.out.println(new Date() + "客户端消息：" + messageRequestPacket.getMessage());
            //对消息响应包进行编码
            ByteBuf messageResponse = PacketCodeC.INSTANCE.encode(ctx.alloc(),messageResponsePacket);
            //写入消息响应包
            ctx.channel().writeAndFlush(messageResponse);
        }
    }

    //登录验证的具体方法
    protected boolean valid(LoginRequestPacket loginRequestPacket){

        loginRequestPacket.getUsername();
        loginRequestPacket.getPassword();

        return true;
    }
}
