package com.tracy.task.rest;

import com.tracy.task.base.RestResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import javax.annotation.Resource;

@Slf4j
public class BaseRest {
    @Resource
    private RedisTemplate<String, String> kvLockTemplate;

    public static final String APPLICATION_JSON_UTF8_VALUE = "application/json;charset=UTF-8";

    public static final String SESSION_PHONE_CAPTCHA = "phoneCaptcha";//验证码
    // 短信验证验证码次数
    public static final String ZHUGE_SMS_CAPTCHA_CNT = "zhuge.sms.captcha.cnt.";
    // 一个小时内获取短信验证验证码次数超过限制则锁定三个小时
    public static final long SMS_CODE_LIMIT_EXPIRY_IN_MINUTES = 180L;
    // 短信验证码有效时间：10分钟
    public static final long SESSION_PHONE_CAPTCHA_EXPIRY_IN_MINUTES = 10L;
    // 上次发送短信验证验证码的时间
    public static final String ZHUGE_SMS_CAPTCHA_SEND_LAST_TIME = "zhuge.sms.captcha.send.last.time.";
    public static final String ZHUGE_SMS_CAPTCHA_SEND_FIRST_TIME = "zhuge.sms.captcha.send.first.time.";

    public static final String REDIS_IMAGE_CODE_KEY = "REDIS_IMAGE_CODE_KEY_";
    public static final Long REDIS_IMAGE_CODE_EXPIRE = 3L;

    public static final String REDIS_USER_LOGIN_ERROR_COUNT = "user_login_error_count_";
    public static final String REDIS_USER_LOGIN_ERROR_TIME = "user_login_error_time_";

    public static final String SESSION_OPEN_ID = "openId";

    protected ResponseEntity success() {
        return new ResponseEntity(RestResult.SUCCESS, HttpStatus.OK);
    }

    //单个值类型
    protected ResponseEntity successValue(Object object) {
        return new ResponseEntity(RestResult.successValue(object), HttpStatus.OK);
    }

    //对象类型
    protected ResponseEntity successObject(Object object) {
        return new ResponseEntity(object, HttpStatus.OK);
    }

    protected ResponseEntity failure(String code, String msg) {
        return new ResponseEntity(new RestResult(code, msg), HttpStatus.BAD_REQUEST);
    }


}
