package com.juejin.session;


import com.juejin.protocol.Attributes;
import io.netty.channel.Channel;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/****************************************************
 *
 * @Description:  会话保存
 *
 * @Author: DONGWENQI
 * @Date: Created in 2:33 PM 2018/12/14
 * @Modified By:
 ****************************************************/
public class SessionUtil {

    private static final Map<String, Channel> userIdMap = new ConcurrentHashMap<>();

    //绑定session
    public static void bindSession(Session session, Channel channel) {
        userIdMap.put(session.getUserId(),channel);
        channel.attr(Attributes.SESSION).set(session);
    }

    public static void unBindSession(Channel channel) {
        if (hasLogin(channel)){
            userIdMap.remove(getSession(channel).getUserId());
            channel.attr(Attributes.SESSION).set(null);
        }
    }

    public static boolean hasLogin(Channel channel) {
        return channel.hasAttr(Attributes.SESSION);
    }

    public static Session getSession(Channel channel) {
        return channel.attr(Attributes.SESSION).get();
    }

    public static Channel getChannel(String userId) {
        return userIdMap.get(userId);
    }
}
