package com.juejin.protocol;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.util.Attribute;
import lombok.Data;

/****************************************************
 *
 * @Description:   登录标记
 *
 * @Author: DONGWENQI
 * @Date: Created in 9:59 AM 2018/12/5
 * @Modified By:
 ****************************************************/
public class LoginUtil {

    public static void markLogin(Channel channel) {channel.attr(Attributes.LOGIN).set(true);}

    public static boolean hasLogin(Channel channel) {

        Attribute<Boolean> login = channel.attr(Attributes.LOGIN);

        return login.get() != null;
    }
}
