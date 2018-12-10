package com.juejin.superclient.code;

import com.juejin.protocol.PacketCodeC;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;

/****************************************************
 *
 * @Description: 解码模块
 *
 * @Author: DONGWENQI
 * @Date: Created in 11:30 AM 2018/12/9
 * @Modified By:
 ****************************************************/
public class PacketDecode extends ByteToMessageDecoder{
    @Override
    protected void decode(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf, List<Object> list) throws Exception {
        list.add(PacketCodeC.INSTANCE.decode(byteBuf));
    }
}
