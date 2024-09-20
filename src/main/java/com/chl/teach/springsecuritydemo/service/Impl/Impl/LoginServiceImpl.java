package com.chl.teach.springsecuritydemo.service.Impl.Impl;

import com.chl.teach.springsecuritydemo.domain.LoginUser;
import com.chl.teach.springsecuritydemo.domain.ResponseResult;
import com.chl.teach.springsecuritydemo.domain.User;
import com.chl.teach.springsecuritydemo.service.Impl.LoginService;
import com.chl.teach.springsecuritydemo.utils.JwtUtil;
import com.chl.teach.springsecuritydemo.utils.RedisCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @author: Chenghl
 * @date: 2024/9/18 16:22
 */
@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    RedisCache redisCache;

    @Override
    public ResponseResult login(User user) {

        // 1.封装Authentication对象
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(user.getUserName(), user.getPassword());

        // 2.通过AuthenticationManager的authenticate方法来进行用户认证
        Authentication authenticated = authenticationManager.authenticate(authenticationToken);
        if (Objects.isNull(authenticated)) {
            throw new RuntimeException("用户名或密码错误");
        }

        // 3.在Authentication中获取用户信息
        LoginUser loginUser = (LoginUser) authenticated.getPrincipal();
        String userId = loginUser.getUser().getId().toString();
        // 4.认证通过生成token
        String jwt = JwtUtil.createJWT(userId);
        // 5.用户信息存入redis
        redisCache.setCacheObject("login:" + userId, loginUser);
        // 6.把token返回给前端
        HashMap<Object, Object> hashMap = new HashMap<>();
        hashMap.put("token", jwt);
        return new ResponseResult(200, "登录成功", hashMap);
    }

    @Override
    public ResponseResult yylogout() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        LoginUser loginUser = (LoginUser) authentication.getPrincipal();
        Long id = loginUser.getUser().getId();
        redisCache.deleteObject("login:" + id);
        return new ResponseResult(200, "注销成功");
    }
}
