package com.juejin.superclient.code;

import com.juejin.protocol.PacketCodeC;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;

/****************************************************
 *
 * @Description:
 *
 * @Author: DONGWENQI
 * @Date: Created in 10:41 AM 2018/12/21
 * @Modified By:
 ****************************************************/
public class Spliter extends LengthFieldBasedFrameDecoder{

    private static final int LENGTH_FIELD_DFFSET = 7;
    private static final int LENGTH_FIELD_LENGTH = 4;

    public Spliter() {
        super(Integer.MAX_VALUE,LENGTH_FIELD_DFFSET,LENGTH_FIELD_LENGTH);
    }

    @Override
    protected Object decode(ChannelHandlerContext ctx, ByteBuf in) throws Exception {
        if (in.getInt(in.readerIndex()) != PacketCodeC.MAGIC_NUMBER) {
            ctx.close();
            return null;
        }
        return super.decode(ctx, in);
    }
}
