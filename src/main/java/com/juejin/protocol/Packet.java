package com.juejin.protocol;

import lombok.Data;

/****************************************************
 *
 * @Description:  定义数据包
 *
 * @Author: DONGWENQI
 * @Date: Created in 8:53 PM 2018/12/1
 * @Modified By:
 ****************************************************/
@Data
public abstract class Packet {

    //协议版本号
    private Byte version = 1;
    //指令
    public abstract Byte getCommand();
}
