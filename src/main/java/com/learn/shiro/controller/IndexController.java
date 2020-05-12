package com.learn.shiro.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.subject.Subject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * IndexController
 *
 * @author chenchen
 * @date 2020/05/12
 */
@RestController
public class IndexController {

    @GetMapping("/login")
    public String index() {
        return "你没有登录！-> 请post请求\"/login\"";
    }

    @GetMapping("/unauth")
    public String unauth() {
        return "你没有权限<_>";
    }

    @PostMapping("/login")
    public String login(String userName, String passWord) {
        // 从SecurityUtils里边创建一个 subject
        Subject subject = SecurityUtils.getSubject();
        // 在认证提交前准备 token（令牌）
        UsernamePasswordToken token = new UsernamePasswordToken(userName, passWord);
        // 执行认证登陆
        try {
            subject.login(token);
        } catch (UnknownAccountException uae) {
            return "未知账户";
        } catch (IncorrectCredentialsException ice) {
            return "密码不正确";
        } catch (LockedAccountException lae) {
            return "账户已锁定";
        } catch (ExcessiveAttemptsException eae) {
            return "用户名或密码错误次数过多";
        } catch (AuthenticationException ae) {
            return "用户名或密码不正确！";
        }
        if (subject.isAuthenticated()) {
            return "登录成功";
        } else {
            token.clear();
            return "登录失败";
        }
    }

    @GetMapping("/welcome")
    public String welcome() {
        return "Welcome";
    }

    @RequiresPermissions("user:list")
    @GetMapping("/user")
    public String user() {
        return "用户列表";
    }

}
