package com.juejin.protocol;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;

/****************************************************
 *
 * @Description:  编解码
 *
 * @Author: DONGWENQI
 * @Date: Created in 4:49 PM 2018/12/2
 * @Modified By:
 ****************************************************/
public class PacketCodeC {

    private static final int MAGIC_NUMBER = 0x12345678;

    //封装成二进制
    public ByteBuf encode(Packet packet) {

        //创建ByteBuf对象
        ByteBuf byteBuf = ByteBufAllocator.DEFAULT.ioBuffer();

        //序列化Java对象
        byte[] bytes = Serializer.DEFAULT.serialize(packet);

        //实际编码过程
        byteBuf.writeByte(MAGIC_NUMBER);
        byteBuf.writeByte(packet.getVersion());
        byteBuf.writeByte(Serializer.DEFAULT.getSerializerAlogrithm());
        byteBuf.writeByte(packet.getCommand());
        byteBuf.writeByte(bytes.length);
        byteBuf.writeBytes(bytes);

        return byteBuf;
    }

    //解析成Java对象
    public Packet decode(ByteBuf byteBuf) {

        //跳过魔数
        byteBuf.skipBytes(4);

        //跳过版本号
        byteBuf.skipBytes(1);

        //得到序列化算法指令
        byte getSerializeAlgorithm = byteBuf.readByte();

        //得到指令
        byte command = byteBuf.readByte();

        //数据包长度
        int length = byteBuf.readInt();

        byte[] bytes = new byte[length];
        byteBuf.readBytes(bytes);

        return null;
    }
}
