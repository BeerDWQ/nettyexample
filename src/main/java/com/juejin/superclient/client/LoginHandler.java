package com.juejin.superclient.client;

import com.juejin.protocol.LoginRequestPacket;
import com.juejin.protocol.LoginResponsePacket;
import com.juejin.protocol.LoginUtil;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.util.Date;

/****************************************************
 *
 * @Description:  客户端登录处理模块
 *
 * @Author: DONGWENQI
 * @Date: Created in 4:26 PM 2018/12/6
 * @Modified By:
 ****************************************************/
public class LoginHandler extends SimpleChannelInboundHandler<LoginResponsePacket>{

    public void channelActive(ChannelHandlerContext ctx) throws Exception {

        LoginRequestPacket loginRequestPacket = new LoginRequestPacket();
        loginRequestPacket.setUserId(1);
        loginRequestPacket.setPassword("");
        loginRequestPacket.setUsername("");
        ctx.channel().writeAndFlush(loginRequestPacket);
    }

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, LoginResponsePacket loginResponsePacket) throws Exception {
        if (loginResponsePacket.isSuccess()) {
            System.out.println(new Date() + "login successful");
            LoginUtil.markLogin(channelHandlerContext.channel());
        }else {
            System.out.println(new Date() + loginResponsePacket.getReason());
        }
    }
}
