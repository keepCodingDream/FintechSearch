package com.tracy.task.shiro;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.apache.shiro.authc.AuthenticationToken;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UsernameToken implements AuthenticationToken {

    private String username;
    private Long userId;

    @Override
    public Object getPrincipal() {
        return username;
    }

    @Override
    public Object getCredentials() {
        return username;
    }
}
