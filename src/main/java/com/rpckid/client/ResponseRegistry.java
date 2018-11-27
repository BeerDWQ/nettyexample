package com.rpckid.client;

import java.util.HashMap;
import java.util.Map;

/****************************************************
 *
 * @Description:   服务端-响应注册中心
 *
 * @Author: DONGWENQI
 * @Date: Created in 3:16 PM 2018/11/23
 * @Modified By:
 ****************************************************/
public class ResponseRegistry {

    private static Map<String, Class<?>> clazzes = new HashMap<>();

    public static void register(String type, Class<?> clazz){
        clazzes.put(type,clazz);
    }

    public static Class<?> get(String type) {
        return clazzes.get(type);
    }
}
