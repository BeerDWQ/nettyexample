package com.juejin.protocol;

/****************************************************
 *
 * @Description:  序列化接口
 *
 * @Author: DONGWENQI
 * @Date: Created in 9:08 PM 2018/12/1
 * @Modified By:
 ****************************************************/
public interface Serializer {

    byte JSON_SERIALIZER = 1;


    Serializer DEFAULT = new JSONSerializer();

    //序列化算法
    byte getSerializerAlogrithm();

    //Java对象转换成二进制
    byte[] serialize(Object object);

    //二进制转换成java对象
    <T> T deserialize(Class<T> clazz, byte[] bytes);

}
