package com.tracy.task.shiro;

import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

@Slf4j
@Component
public class UsernamePasswordRealm extends AuthorizingRealm {

    private static final String REDIS_USER_LOGIN_ERROR_COUNT = "user_login_error_count_";
    private static final String REDIS_USER_LOGIN_ERROR_TIME = "user_login_error_time_";

    @Resource
    RedisTemplate<String, String> kvLockTemplate;

    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof UsernamePasswordToken;
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        return null;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
//        User user = userService.getByUserName(token.getUsername());
//        if (user != null) {
//            if (DigestUtils.md5DigestAsHex(new String(token.getPassword()).getBytes()).equals(user.getPassword())) {
//                CachedUser cachedUser = new CachedUser();
//                cachedUser.setUserId(user.getId());
//                SimpleAccount simpleAccount = new SimpleAccount(cachedUser, authenticationToken.getCredentials(), "usernamePasswordRealm");
//                return simpleAccount;
//            }
//        }

        //添加逻辑：若用户一小时内密码错误5次，则锁定三小时不允许登录
        String errorCount = kvLockTemplate.opsForValue().get(REDIS_USER_LOGIN_ERROR_COUNT + token.getUsername());
        Integer err_cnt = errorCount == null ? 0 : Integer.valueOf(errorCount);
        log.info("user " + token.getUsername() + "login error, error_cnt = " + (err_cnt + 1));
        if (err_cnt == 0) {
            err_cnt = 1;
            Long now = System.currentTimeMillis();
            kvLockTemplate.opsForValue().set(REDIS_USER_LOGIN_ERROR_TIME + token.getUsername(), now.toString(), 60L, TimeUnit.MINUTES);
            kvLockTemplate.opsForValue().set(REDIS_USER_LOGIN_ERROR_COUNT + token.getUsername(), err_cnt.toString(), 60L, TimeUnit.MINUTES);
        } else if (err_cnt > 0 && err_cnt < 5) {
            String last_error_time = kvLockTemplate.opsForValue().get(REDIS_USER_LOGIN_ERROR_TIME + token.getUsername());
            Long lastSent = Long.valueOf(last_error_time);
            Long now = System.currentTimeMillis();
            Long expire_time = 60 - (now - lastSent) / 1000 / 60;
            err_cnt = err_cnt + 1;
            kvLockTemplate.opsForValue().set(REDIS_USER_LOGIN_ERROR_COUNT + token.getUsername(), err_cnt.toString(), expire_time, TimeUnit.MINUTES);
        }

        throw new AuthenticationException(token.getUsername() + "用户名不正确或密码错误!");
    }
}
