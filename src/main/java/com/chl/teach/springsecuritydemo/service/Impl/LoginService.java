package com.chl.teach.springsecuritydemo.service.Impl;

import com.chl.teach.springsecuritydemo.domain.ResponseResult;
import com.chl.teach.springsecuritydemo.domain.User;
import org.springframework.stereotype.Service;

/**
 * @author: Chenghl
 * @date: 2024/9/18 16:21
 */

public interface LoginService {
    ResponseResult login(User user);
}
