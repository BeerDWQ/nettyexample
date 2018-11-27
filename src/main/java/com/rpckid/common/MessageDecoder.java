package com.rpckid.common;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.DecoderException;
import io.netty.handler.codec.ReplayingDecoder;


import java.nio.charset.Charset;
import java.util.List;

/****************************************************
 *
 * @Description:  服务器-消息解码器
 *
 * @Author: DONGWENQI
 * @Date: Created in 9:06 AM 2018/11/22
 * @Modified By:
 ****************************************************/
public class MessageDecoder extends ReplayingDecoder{

    @Override
    protected void decode(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf, List<Object> list) throws Exception {

    }

    private String readStr(ByteBuf in) {
        int len = in.readInt();
        if(len<0 || len> (1<<20)) {
            throw new DecoderException("String is too long " + len);
        }
        byte[] bytes = new byte[len];
        in.readBytes(bytes);
        return new String(bytes, Charset.defaultCharset());
    }
}
