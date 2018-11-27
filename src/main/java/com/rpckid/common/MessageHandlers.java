package com.rpckid.common;

import com.rpckid.common.IMessageHandler;
import com.rpckid.server.DefaultHandler;

import java.util.HashMap;
import java.util.Map;

/****************************************************
 *
 * @Description:    服务器-注册处理类
 *
 * @Author: DONGWENQI
 * @Date: Created in 3:13 PM 2018/11/22
 * @Modified By:
 ****************************************************/
public class MessageHandlers {

    private static Map<String, IMessageHandler<?>> handlers = new HashMap<>();

    private IMessageHandler<MessageInput> defaultHandler;

    public MessageHandlers defaultHandler(IMessageHandler<MessageInput> defaultHandler) {
        this.defaultHandler = defaultHandler;
        return this;
    }

    public static void register(String type, IMessageHandler<?> handler) {
        handlers.put(type, handler);
    }

    public static IMessageHandler<?> get(String type) {
        return handlers.get(type);
    }

    public IMessageHandler<MessageInput> defaultHandler() {
        return defaultHandler;
    }

}
