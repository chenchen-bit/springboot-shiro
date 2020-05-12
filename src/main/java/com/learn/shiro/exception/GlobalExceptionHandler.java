package com.learn.shiro.exception;

import org.apache.shiro.authz.AuthorizationException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 全局异常处理器
 *
 * @author chenchen
 * @date 2020/05/12
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * 权限校验失败
     *
     * @param e
     * @return
     */
    @ExceptionHandler(AuthorizationException.class)
    public String handleAuthorizationException(AuthorizationException e) {
        System.out.println("没有权限->" + e.getMessage());
        return "你没有权限<_>";
    }

}
