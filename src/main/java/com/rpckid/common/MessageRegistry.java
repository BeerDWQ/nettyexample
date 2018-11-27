package com.rpckid.common;

import java.util.HashMap;
import java.util.Map;

/****************************************************
 *
 * @Description:    服务器-注册中心
 *
 * @Author: DONGWENQI
 * @Date: Created in 3:00 PM 2018/11/22
 * @Modified By:
 ****************************************************/
public class MessageRegistry {

    public static Map<String, Class<?>> clazzes = new HashMap<>();

    public static void register(String type, Class<?> clazz) {
        clazzes.put(type,clazz);
    }

    public static Class<?> get(String type) {
        return clazzes.get(type);
    }
}
