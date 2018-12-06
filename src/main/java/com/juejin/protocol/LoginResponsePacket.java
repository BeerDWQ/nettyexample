package com.juejin.protocol;

import lombok.Data;

import static com.juejin.protocol.Command.LOGIN_RESPONSE;

/****************************************************
 *
 * @Description:  登录响应包
 *
 * @Author: DONGWENQI
 * @Date: Created in 9:33 AM 2018/12/5
 * @Modified By:
 ****************************************************/
@Data
public class LoginResponsePacket extends Packet{

    private boolean success;
    private String reason;

    public Byte getCommand() {return LOGIN_RESPONSE;}
}
