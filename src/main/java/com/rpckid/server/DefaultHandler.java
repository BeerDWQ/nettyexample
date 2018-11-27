package com.rpckid.server;

import com.rpckid.common.IMessageHandler;
import com.rpckid.common.MessageInput;
import io.netty.channel.ChannelHandlerContext;

/****************************************************
 *
 * @Description:
 *
 * @Author: DONGWENQI
 * @Date: Created in 2:47 PM 2018/11/22
 * @Modified By:
 ****************************************************/
public class DefaultHandler implements IMessageHandler<MessageInput> {

    @Override
    public void handle(ChannelHandlerContext ctx, String reqestId, MessageInput input) {
        System.out.println("unrecognized message type=" + input.getType() + " comes");
    }
}
