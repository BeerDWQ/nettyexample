package com.juejin.superclient.client;

import com.juejin.protocol.MessageResponsePacket;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.util.Date;

/****************************************************
 *
 * @Description:  客户端消息处理模块
 *
 * @Author: DONGWENQI
 * @Date: Created in 4:18 PM 2018/12/6
 * @Modified By:
 ****************************************************/
public class MessageResponseHandler extends SimpleChannelInboundHandler<MessageResponsePacket> {

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, MessageResponsePacket messageResponsePacket) throws Exception {
        System.out.println(new Date() + "receive message");
    }
}
