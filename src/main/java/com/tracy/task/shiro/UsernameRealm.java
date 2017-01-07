package com.tracy.task.shiro;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAccount;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

public class UsernameRealm extends AuthorizingRealm {

    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof UsernameToken;
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        return null;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        UsernameToken token = (UsernameToken) authenticationToken;
        Long userId = token.getUserId();
        if (userId != null) {
            CachedUser cachedUser = new CachedUser();
            cachedUser.setUserId(userId);
            return new SimpleAccount(cachedUser, authenticationToken.getCredentials(), "usernameRealm");
        }
        throw new AuthenticationException(token.getUsername() + "用户名不正确或密码错误!");
    }
}
