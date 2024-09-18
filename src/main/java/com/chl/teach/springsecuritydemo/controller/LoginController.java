package com.chl.teach.springsecuritydemo.controller;

import com.chl.teach.springsecuritydemo.domain.ResponseResult;
import com.chl.teach.springsecuritydemo.domain.User;
import com.chl.teach.springsecuritydemo.service.Impl.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: Chenghl
 * @date: 2024/9/18 16:27
 */
@RestController
public class LoginController {
    @Autowired
    private LoginService loginService;

    @PostMapping("/user/login")
    public ResponseResult login(@RequestBody User user) {
        return loginService.login(user);
    }
}
