package com.chl.teach.springsecuritydemo.domain;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author: Chenghl
 * @date: 2024/9/18 15:00
 */

@Data
@NoArgsConstructor
public class LoginUser implements UserDetails {

    private User user;// 封装用户信息
    private List<String> permissions;

    public LoginUser(User user, List<String> permissions) {
        this.user = user;
        this.permissions = permissions;
    }

    @JSONField(serialize = false)
    private List<SimpleGrantedAuthority> authorities;

    // 获取权限
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if (authorities != null) {
            // 严谨来说这个if判断是避免整个调用链中security查询用户权限时重复计算
            return authorities;
        }
        // 为空的话就会执行下面的转换代码
        authorities = permissions
                .stream()
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
        // 最终返回转换结果
        return authorities;
    }

    // 获取密码
    @Override
    public String getPassword() {
        return user.getPassword();
    }

    // 获取用户名
    @Override
    public String getUsername() {
        return user.getUserName();
    }

    // 账户是否未过期
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    // 账户是否未锁定
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    // 密码是否未过期
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    // 账户是否可用
    @Override
    public boolean isEnabled() {
        return true;
    }

}
