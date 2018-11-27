package com.rpckid.client;

/****************************************************
 *
 * @Description:   客户端
 *
 * @Author: DONGWENQI
 * @Date: Created in 3:05 PM 2018/11/23
 * @Modified By:
 ****************************************************/
public class RPCRequest {

    private String requestId;
    private String type;
    private Object payLoad;

    public RPCRequest(String requestId, String type, Object payLoad) {
        this.requestId = requestId;
        this.type = type;
        this.payLoad = payLoad;
    }

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Object getPayLoad() {
        return payLoad;
    }

    public void setPayLoad(Object payLoad) {
        this.payLoad = payLoad;
    }
}
