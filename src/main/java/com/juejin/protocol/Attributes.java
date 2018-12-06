package com.juejin.protocol;

import io.netty.util.AttributeKey;

/****************************************************
 *
 * @Description:
 *
 * @Author: DONGWENQI
 * @Date: Created in 10:04 AM 2018/12/5
 * @Modified By:
 ****************************************************/
public interface Attributes {

    AttributeKey<Boolean> LOGIN = AttributeKey.newInstance("login");
}
