package com.juejin.superclient.client;

import com.juejin.protocol.LoginRequestPacket;
import com.juejin.protocol.LoginUtil;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/****************************************************
 *
 * @Description:    登录热拔插
 *
 * @Author: DONGWENQI
 * @Date: Created in 10:10 AM 2018/12/14
 * @Modified By:
 ****************************************************/
public class AuthHandler extends ChannelInboundHandlerAdapter{

    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        if (!LoginUtil.hasLogin(ctx.channel())) {
            ctx.channel().close();
        }else {
            ctx.pipeline().remove(this);
            super.channelRead(ctx,msg);
        }
    }

    public void handlerRemoved(ChannelHandlerContext ctx) {
        if (LoginUtil.hasLogin(ctx.channel())) {
            System.out.println("已经登录过，无需验证");
        }else {
            System.out.println("未登录，退出");
        }
    }
}
