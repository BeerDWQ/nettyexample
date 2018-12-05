package com.juejin.protocol;

/****************************************************
 *
 * @Description:
 *
 * @Author: DONGWENQI
 * @Date: Created in 9:02 PM 2018/12/1
 * @Modified By:
 ****************************************************/
public interface Command {

    Byte LOGIN_REQUEST = 1;

    Byte LOGIN_RESPONSE =2;

    Byte MESSAGE_REQUEST =3;

    Byte MESSAGE_RESPONSE =4;
}
