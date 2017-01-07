package com.tracy.task.shiro;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.springframework.stereotype.Component;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
@Component
public class RestAuthFilter extends FormAuthenticationFilter {

    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
        httpServletResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        return false;
    }

    @Override
    protected boolean onLoginSuccess(AuthenticationToken token, Subject subject, ServletRequest request, ServletResponse response) throws Exception {
        return false;
    }

    @Override
    protected boolean onLoginFailure(AuthenticationToken token, AuthenticationException e, ServletRequest request, ServletResponse response) {
        HttpServletRequest req = (HttpServletRequest) request;
        log.error("401 Unauthorized {}", req.getRequestURI());
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
        httpServletResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        try {
            httpServletResponse.getWriter().println("{msg: '请重新登录！', code:'-1'}");
        } catch (Exception e1) {
            //ignore
        }
        return false;
    }

    @Override
    protected boolean isAccessAllowed(ServletRequest request,
                                      ServletResponse response,
                                      Object mappedValue) {
        return super.isAccessAllowed(request, response, mappedValue) || super.isAccessAllowed(request, response, mappedValue);
    }



    private UsernamePasswordToken getUsernamePasswordToken(HttpServletRequest httpRequest) {
        String username = httpRequest.getHeader("X-AUTH-USERNAME");
        String passwd = httpRequest.getHeader("X-AUTH-PASSWORD");
        if (StringUtils.isNotEmpty(username) && StringUtils.isNotEmpty(passwd)) {
            return new UsernamePasswordToken(username, passwd);
        }
        return null;
    }

}
