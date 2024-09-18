package com.chl.teach.springsecuritydemo;

import com.chl.teach.springsecuritydemo.domain.User;
import com.chl.teach.springsecuritydemo.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

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

    @Test
    public void TestBcryptPasswordEncoder(){
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        String encode1 = bCryptPasswordEncoder.encode("1234");
        System.out.println(encode1);

        boolean matches = bCryptPasswordEncoder.matches("1234", encode1);
        System.out.println(matches);

    }
}
