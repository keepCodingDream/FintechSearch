package com.tracy.task.handler;

import com.tracy.task.base.RestResult;
import com.tracy.task.exception.BusinessException;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.UUID;

/**
 * Created by Tracy on 2016/3/21
 */
@Slf4j
@ControllerAdvice(basePackages = {"com.tracy"})
@RestController
public class GlobalExceptionHandler {

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public ResponseEntity resolveException(HttpServletRequest request, Exception e) {
        log.error("Exception caught by CashBusExceptionHandler, exception info：" + e.getMessage(), e);
        if (e instanceof AuthenticationException) {
            return new ResponseEntity(RestResult.defaultFailure("手机号或密码输入错误啦"), HttpStatus.BAD_REQUEST);
        } else if (e instanceof UnauthorizedException) {
            return new ResponseEntity(RestResult.defaultFailure("未登录"), HttpStatus.UNAUTHORIZED);
        } else if (e instanceof BusinessException) {
            return new ResponseEntity(RestResult.defaultFailure(e.getMessage()), HttpStatus.BAD_REQUEST);
        /*} else if (e instanceof NullPointerException) {
            return new ResponseEntity(RestResult.defaultFailure("NPE"), HttpStatus.INTERNAL_SERVER_ERROR);
        } else if(e instanceof MySQLSyntaxErrorException){
            return new ResponseEntity(RestResult.defaultFailure("数据库异常"), HttpStatus.INTERNAL_SERVER_ERROR);*/
        } else {
            String uuid = UUID.randomUUID().toString();
            log.error(uuid + "CashBusExceptionHandler", e);
            return new ResponseEntity(new RestResult(uuid, "忙不过来啦，请主公等等"), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
