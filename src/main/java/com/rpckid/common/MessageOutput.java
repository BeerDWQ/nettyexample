package com.rpckid.common;

/****************************************************
 *
 * @Description:  服务器-输出
 *
 * @Author: DONGWENQI
 * @Date: Created in 9:03 AM 2018/11/22
 * @Modified By:
 ****************************************************/
public class MessageOutput {
    //输出类型
    private String type;
    //消息id
    private String id;
    //消息的json序列化字符串内容
    private String payLoad;

    public MessageOutput(String type, String id, String payLoad) {
        this.type = type;
        this.id = id;
        this.payLoad = payLoad;
    }

    public String getType() {
        return type;
    }

    public String getId() {
        return id;
    }

    public String getPayLoad() {
        return payLoad;
    }
}
