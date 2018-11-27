package com.rpckid.client;


import com.alibaba.fastjson.JSON;
import com.rpckid.common.Charsets;
import com.rpckid.common.RequestId;

import java.io.*;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;

/****************************************************
 *
 * @Description:  客户端
 *
 * @Author: DONGWENQI
 * @Date: Created in 3:28 PM 2018/11/23
 * @Modified By:
 ****************************************************/
public class RPCClient {

    private String ip;
    private int port;
    private Socket socket;
    private DataInputStream inputStream;
    private OutputStream outputStream;

    public RPCClient(String ip,int port) {
        this.ip = ip;
        this.port = port;
    }

    public void connect() throws Exception {

        SocketAddress address = new InetSocketAddress(ip,port);
        socket = new Socket();
        socket.connect(address,5000);
        inputStream = new DataInputStream(socket.getInputStream());
        outputStream = socket.getOutputStream();
    }

    public void cast(String type, Object payload) {
        try {
            this.sendInternal(type, payload, true);
        } catch (IOException e) {
            throw new RPCException(e);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void close() {
        // 关闭链接
        try {
            socket.close();
            socket = null;
            inputStream = null;
            outputStream = null;
        } catch (IOException e) {
        }
    }

    public Object sendInternal(String type, Object payLoad, boolean cast) throws Exception{

        if (outputStream == null) {
            connect();
        }

        String requestId = RequestId.next();
        ByteArrayOutputStream buf = new ByteArrayOutputStream();
        DataOutputStream bufs = new DataOutputStream(buf);
        writeStr(bufs,requestId);
        writeStr(bufs,type);
        writeStr(bufs, JSON.toJSONString(payLoad));
        buf.flush();
        byte[] fullLoad = buf.toByteArray();
        try {
            // 发送请求
            outputStream.write(fullLoad);
        } catch (IOException e) {
            // 网络异常要重连
            close();
            connect();
            outputStream.write(fullLoad);
        }
        if (!cast) {
            // RPC普通请求，要立即获取响应
            String reqId = readStr();
            // 校验请求ID是否匹配
            if (!requestId.equals(reqId)) {
                close();
                throw new RPCException("request id mismatch");
            }
            String typ = readStr();
            Class<?> clazz = ResponseRegistry.get(typ);
            // 响应类型必须提前注册
            if (clazz == null) {
                throw new RPCException("unrecognized rpc response type=" + typ);
            }
            // 反序列化json串
            String payld = readStr();
            Object res = JSON.parseObject(payld, clazz);
            return res;
        }
        return null;
    }

    public String readStr() throws IOException{
        int len = inputStream.readInt();
        byte[] bytes = new byte[len];
        inputStream.readFully(bytes);
        return new String(bytes,Charsets.UTF8);
    }

    public void writeStr(DataOutputStream out, String s) throws IOException{

        out.writeInt(s.length());
        out.write(s.getBytes(Charsets.UTF8));

    }
}
