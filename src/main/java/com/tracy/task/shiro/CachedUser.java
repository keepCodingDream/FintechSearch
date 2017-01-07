package com.tracy.task.shiro;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;

@Slf4j
@Data
public class CachedUser implements Serializable{
    Long userId;
    String openId;
    Long ttl;

    static final String hashKey = "cashbusSSO*&^%";
    public static final String redisTokenPrefix = "sso.token.";
    public static final String tokenCookieName = "X-AUTH-TOKEN";

    private String genToken() throws UnsupportedEncodingException {
        return null;//MD5.md5(this.userId + this.ttl + "", "cashbusSSO*&^%");
    }

    public boolean verify(String token) {
        try {
            String e = this.genToken();
            assert false;
            if (!e.equals(token)) {
                log.error("Wrong token {} expected {}: user {}", new Object[]{token, e, this.userId});
                return false;
            } else if (System.currentTimeMillis() > this.ttl) {
                log.error("Token has expired: " + this.ttl);
                return false;
            } else {
                return true;
            }
        } catch (UnsupportedEncodingException var3) {
            log.error("", var3);
            return false;
        }
    }
}
