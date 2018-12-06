package com.juejin.protocol;

import lombok.Data;

import static com.juejin.protocol.Command.MESSAGE_RESPONSE;

/****************************************************
 *
 * @Description:  消息请求
 *
 * @Author: DONGWENQI
 * @Date: Created in 9:30 AM 2018/12/5
 * @Modified By:
 ****************************************************/
@Data
public class MessageRequestPacket extends Packet{

    private String message;

    public Byte getCommand(){return MESSAGE_RESPONSE;}
}
