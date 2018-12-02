package com.juejin.protocol;

import lombok.Data;

/****************************************************
 *
 * @Description:  登陆数据包
 *
 * @Author: DONGWENQI
 * @Date: Created in 9:03 PM 2018/12/1
 * @Modified By:
 ****************************************************/
@Data
public class LoginRequestPacket extends Packet implements Command{

    private Integer userId;

    private String username;

    private String password;

    @Override
    public Byte getCommand() {
        return LOGIN_REQUEST;
    }
}
