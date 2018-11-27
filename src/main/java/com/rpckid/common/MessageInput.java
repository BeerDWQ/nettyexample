package com.rpckid.common;

import com.alibaba.fastjson.JSON;

/****************************************************
 *
 * @Description:  服务器-请求
 *
 * @Author: DONGWENQI
 * @Date: Created in 8:47 AM 2018/11/22
 * @Modified By:
 ****************************************************/
public class MessageInput {
    //消息类型
    private String type;
    //消息Id
    private String id;
    //序列化json内容
    private String payLoad;

    public MessageInput(String type, String id, String payLoad) {
        this.type = type;
        this.id = id;
        this.payLoad = payLoad;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getId() {
        return id;
    }

    //获取对象类型信息
    public <T> T getPayLoad(Class<T> clazz) {
        if(payLoad == null) {
            return null;
        }
        return JSON.parseObject(payLoad,clazz);
    }

}
