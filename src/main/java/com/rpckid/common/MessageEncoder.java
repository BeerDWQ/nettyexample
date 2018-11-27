package com.rpckid.common;

import com.alibaba.fastjson.JSON;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.PooledByteBufAllocator;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageEncoder;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.List;

/****************************************************
 *
 * @Description:   服务器-响应消息的编码器
 *
 * @Author: DONGWENQI
 * @Date: Created in 3:23 PM 2018/11/22
 * @Modified By:
 ****************************************************/
public class MessageEncoder extends MessageToMessageEncoder<MessageOutput>{

    @Override
    protected void encode(ChannelHandlerContext channelHandlerContext, MessageOutput messageOutput, List<Object> list) throws Exception {
        ByteBuf buf = PooledByteBufAllocator.DEFAULT.directBuffer();
        writeStr(buf,messageOutput.getType());
        writeStr(buf,messageOutput.getId());
        writeStr(buf, JSON.toJSONString(messageOutput.getPayLoad()));
    }

    private void writeStr (ByteBuf buf, String s) {
        buf.writeInt(s.length());
        buf.writeBytes(s.getBytes(Charsets.UTF8));
    }
}
