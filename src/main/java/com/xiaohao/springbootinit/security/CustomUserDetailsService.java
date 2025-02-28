package com.xiaohao.springbootinit.security;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.xiaohao.springbootinit.mapper.UserMapper;
import com.xiaohao.springbootinit.model.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Slf4j
public class CustomUserDetailsService implements UserDetailsService {
    @Resource
    private UserMapper userMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.debug("开始进行登录验证，用户名：{}", username);
        // 访问数据库进行对用户的搜索
        LambdaQueryWrapper<User> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(User::getUserAccount, username);
        User user = userMapper.selectOne(lambdaQueryWrapper);
        if (user == null) {
            // 用户不存在
            throw new UsernameNotFoundException("UserAccount:" + username + " not found");
        }
        // 返回UserDetail对象
        return user;
    }
}
