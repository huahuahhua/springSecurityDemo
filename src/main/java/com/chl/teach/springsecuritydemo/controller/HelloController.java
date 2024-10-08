package com.chl.teach.springsecuritydemo.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: Chenghl
 * @date: 2024/9/18 14:17
 */
@RestController
@PreAuthorize("hasAuthority('test')")
public class HelloController {
    @RequestMapping("hello")
    public String hello(){
        return "欢迎，开启你的学习";
    }

}
