package com.juejin.aboutbytebuf;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;

/****************************************************
 *
 * @Description:  ByteBuf测试
 *
 * @Author: DONGWENQI
 * @Date: Created in 10:11 AM 2018/11/30
 * @Modified By:
 ****************************************************/
public class ByteBufTest {

    public static void main(String[] args) {

        ByteBuf buf = ByteBufAllocator.DEFAULT.buffer(9,100);

        print("allocate ByteBuf(9, 100)", buf);

        //写入数据
        buf.writeBytes(new byte[]{1,2,3,4});
        print("writeBytes(1,2,3,4)",buf);

        //写入int类型数据后空间增加4
        buf.writeInt(12);
        print("writeInt(12)",buf);

        //写入数据使容量到达最大值
        buf.writeBytes(new byte[]{5});
        print("writeBytes(5)", buf);

        //数据数量超过最大值以后扩容
        buf.writeBytes(new byte[]{6});
        print("writeBytes(6)", buf);

        //get方法
        System.out.println("getByte(3) return: " + buf.getByte(3));
        System.out.println("getShort(3) return: " + buf.getShort(3));
        System.out.println("getInt(3) return: " + buf.getInt(3));
        print("getByte()", buf);

        //set
        buf.setByte(buf.readableBytes()+1,0);

        //read
        byte[] dst = new byte[buf.readableBytes()];
        buf.readBytes(dst);
        print("read byte" + dst.length + ")", buf);
    }

    protected static void print(String action, ByteBuf buffer) {

        System.out.println("after ===========" + action + "============");
        System.out.println("capacity(): " + buffer.capacity());
        System.out.println("maxCapacity(): " + buffer.maxCapacity());
        System.out.println("readerIndex(): " + buffer.readerIndex());
        System.out.println("readableBytes(): " + buffer.readableBytes());
        System.out.println("isReadable(): " + buffer.isReadable());
        System.out.println("writerIndex(): " + buffer.writerIndex());
        System.out.println("writableBytes(): " + buffer.writableBytes());
        System.out.println("isWritable(): " + buffer.isWritable());
        System.out.println("maxWritableBytes(): " + buffer.maxWritableBytes());
        System.out.println();
    }
}
