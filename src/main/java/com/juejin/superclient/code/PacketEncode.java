package com.juejin.superclient.code;

import com.juejin.protocol.Packet;
import com.juejin.protocol.PacketCodeC;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

/****************************************************
 *
 * @Description:  模块化客户端解码
 *
 * @Author: DONGWENQI
 * @Date: Created in 11:35 AM 2018/12/9
 * @Modified By:
 ****************************************************/
public class PacketEncode extends MessageToByteEncoder<Packet>{
    @Override
    protected void encode(ChannelHandlerContext channelHandlerContext, Packet packet, ByteBuf byteBuf) throws Exception {
        PacketCodeC.INSTANCE.encodeModel(byteBuf,packet);
    }
}
