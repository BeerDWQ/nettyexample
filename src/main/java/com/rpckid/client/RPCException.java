package com.rpckid.client;

/****************************************************
 *
 * @Description:   客户端-异常处理
 *
 * @Author: DONGWENQI
 * @Date: Created in 3:09 PM 2018/11/23
 * @Modified By:
 ****************************************************/
public class RPCException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public RPCException(String message, Throwable cause) {
        super(message,cause);
    }

    public RPCException(Throwable cause) {
        super(cause);
    }

    public RPCException(String message) {
        super(message);
    }
}
