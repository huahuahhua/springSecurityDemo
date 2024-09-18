package com.chl.teach.springsecuritydemo;

import com.chl.teach.springsecuritydemo.domain.User;
import com.chl.teach.springsecuritydemo.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

/**
 * @author: Chenghl
 * @date: 2024/9/18 14:46
 */
@SpringBootTest
public class MapperTest {
    @Autowired
    private UserMapper userMapper;

    @Test
    public void testUserMapper() {
// 查询所有用户
        List<User> users = userMapper.selectList(null);
        System.out.println(users);
    }
}
