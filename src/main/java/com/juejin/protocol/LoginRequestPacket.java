package com.juejin.protocol;

import lombok.Data;

import static com.juejin.protocol.Command.LOGIN_REQUEST;

/****************************************************
 *
 * @Description:  登陆数据包
 *
 * @Author: DONGWENQI
 * @Date: Created in 9:03 PM 2018/12/1
 * @Modified By:
 ****************************************************/
@Data
public class LoginRequestPacket extends Packet{

    private Integer userId;

    private String username;

    private String password;

    @Override
    public Byte getCommand() {
        return LOGIN_REQUEST;
    }
}
