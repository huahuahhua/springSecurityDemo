package com.chl.teach.springsecuritydemo.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.chl.teach.springsecuritydemo.domain.LoginUser;
import com.chl.teach.springsecuritydemo.domain.User;
import com.chl.teach.springsecuritydemo.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Objects;

/**
 * @author: Chenghl
 * @date: 2024/9/18 14:57
 */

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    UserMapper userMapper;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        // 根据用户名查询用户信息
        LambdaQueryWrapper wrapper = new LambdaQueryWrapper<User>().eq(User::getUserName, username);
        User user = userMapper.selectOne(wrapper);

        //如果没有该用户就抛出异常
        if (Objects.isNull(user)) {
            throw new RuntimeException("用户名或密码错误");
        }

        //TODO: 查询权限信息封装到LoginUser中

        // 将用户信息封装到UserDetails实现类中
        return new LoginUser(user);
    }
}
