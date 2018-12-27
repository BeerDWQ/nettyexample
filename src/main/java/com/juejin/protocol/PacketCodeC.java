package com.juejin.protocol;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;

import java.util.HashMap;
import java.util.Map;

import static com.juejin.protocol.Command.*;

/****************************************************
 *
 * @Description:  编解码
 *
 * @Author: DONGWENQI
 * @Date: Created in 4:49 PM 2018/12/2
 * @Modified By:
 ****************************************************/
public class PacketCodeC {

    public static final int MAGIC_NUMBER = 0x12345678;
    public static final PacketCodeC INSTANCE = new PacketCodeC();

    //数据包类型
    private static final Map<Byte,Class<? extends Packet>>  packetTypeMap;
    //序列化类型
    private static final Map<Byte,Serializer> serializeTypeMap;

    static {
        packetTypeMap = new HashMap<>();
        packetTypeMap.put(LOGIN_REQUEST,LoginRequestPacket.class);
        packetTypeMap.put(LOGIN_RESPONSE,LoginResponsePacket.class);
        packetTypeMap.put(MESSAGE_REQUEST,MessageRequestPacket.class);
        packetTypeMap.put(MESSAGE_RESPONSE,MessageResponsePacket.class);

        Serializer serializer = new JSONSerializer();
        serializeTypeMap = new HashMap<>();
        serializeTypeMap.put(serializer.getSerializerAlogrithm(),serializer);
    }

    //封装成二进制
    public ByteBuf encode(ByteBufAllocator byteBufAllocator,Packet packet) {

        //创建ByteBuf对象
        ByteBuf byteBuf = byteBufAllocator.ioBuffer();

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

    //模块化编码
    public ByteBuf encodeModel(ByteBuf byteBuf,Packet packet) {


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

        //读取数据
        byte[] bytes = new byte[length];
        byteBuf.readBytes(bytes);

        //确定数据包类型和序列化算法
        Class<? extends Packet> packetType = getRequestType(command);
        Serializer serializer = getSerializer(getSerializeAlgorithm);

        //反序列化对象
        if(packetType != null && serializer != null) {
            serializer.deserialize(packetType,bytes);
        }

        return null;
    }

    //得到请求类型
    protected Class<? extends Packet> getRequestType(byte command) {
        return packetTypeMap.get(command);
    }

    //得到序列化算法
    protected Serializer getSerializer(byte serializerAlgorithm) {
        return serializeTypeMap.get(serializerAlgorithm);
    }
}
