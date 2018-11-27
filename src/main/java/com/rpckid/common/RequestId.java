package com.rpckid.common;

import java.util.UUID;

/****************************************************
 *
 * @Description:   生成请求ID
 *
 * @Author: DONGWENQI
 * @Date: Created in 3:13 PM 2018/11/23
 * @Modified By:
 ****************************************************/
public class RequestId {

    public static String next() {
        return UUID.randomUUID().toString();
    }

}
