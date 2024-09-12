package com.chl.teach.springsecuritydemo.common;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: Chenghl
 * @date: 2024/9/12 11:06
 */
@RestController
public class hello {
    @RequestMapping("/hello")
    public String hello() {
        return "hello";
    }
}
