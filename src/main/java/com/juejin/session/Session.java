package com.juejin.session;

import lombok.Data;
import lombok.NoArgsConstructor;

/****************************************************
 *
 * @Description: session实体
 *
 * @Author: DONGWENQI
 * @Date: Created in 2:35 PM 2018/12/14
 * @Modified By:
 ****************************************************/
@Data
@NoArgsConstructor
public class Session {

    private String userId;
    private String userName;

    public void Session(String userId, String userName) {
        this.userId = userId;
        this.userName = userName;
    }

    @Override
    public String toString() {
        return "Session{" +
                "userId='" + userId + '\'' +
                ", userName='" + userName + '\'' +
                '}';
    }
}
