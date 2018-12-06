package com.juejin.protocol;

import lombok.Data;

import static com.juejin.protocol.Command.MESSAGE_RESPONSE;

/****************************************************
 *
 * @Description:  消息想响应包
 *
 * @Author: DONGWENQI
 * @Date: Created in 9:34 AM 2018/12/5
 * @Modified By:
 ****************************************************/
@Data
public class MessageResponsePacket extends Packet{

    private String message;

    public Byte getCommand() {return MESSAGE_RESPONSE;}
}
