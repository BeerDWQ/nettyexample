package com.rpckid.common;

import io.netty.channel.ChannelHandlerContext;

/****************************************************
 *
 * @Description:   服务器-处理
 *
 * @Author: DONGWENQI
 * @Date: Created in 2:44 PM 2018/11/22
 * @Modified By:
 ****************************************************/
public interface IMessageHandler<T>{

    void handle(ChannelHandlerContext ctx, String reqestId, T message);

}
