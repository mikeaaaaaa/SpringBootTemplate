package com.xiaohao.springbootinit.security;


import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import com.xiaohao.springbootinit.constant.RedisConstant;
import com.xiaohao.springbootinit.model.entity.User;
import com.xiaohao.springbootinit.service.UserService;
import io.jsonwebtoken.Claims;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.annotation.Resource;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * <p>
 * JWT登录过滤器
 * </p>
 * <p>
 * 拿到请求头中的token解析出其中的用户信息，
 * 将用户信息传给下一条过滤器，
 * 拿到上下文对象赋值到上下文。
 * <p>
 *
 * @author 和耳朵
 * @since 2020-06-30
 */
@Slf4j
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {

    @Resource
    private SecurityProperties securityProperties;


    @Resource
    private StringRedisTemplate stringRedisTemplate;


    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain chain) throws ServletException, IOException {
        log.info("JWT过滤器通过校验请求头token进行自动登录...");

        // 拿到Authorization请求头内的信息
        String authToken = JwtUtil.getToken(request, securityProperties.getTokenName());

        // 判断一下内容是否为空
        if (StrUtil.isNotEmpty(authToken) && authToken.startsWith(securityProperties.getTokenPrefix())) {
            // 去掉token前缀(Bearer )，拿到真实token
            authToken = authToken.substring(securityProperties.getTokenPrefix().length());

            // 拿到token里面的登录账号
            Claims claims = JwtUtil.parseJWT(securityProperties.getSecretKey(), authToken);
            String loginAccount = claims.get(securityProperties.getClaimsUsername()).toString();

            if (StrUtil.isNotEmpty(loginAccount) && SecurityContextHolder.getContext().getAuthentication() == null) {
                // todo 拿到用户信息,可以先设置从数据库读取！！！
                String userStr = (String) stringRedisTemplate.opsForValue().get(RedisConstant.USER_KEY + loginAccount);
                User user = JSONUtil.toBean(userStr, User.class);

                // 拿到用户信息后验证用户信息与token
                if (user != null) {
                    // 组装authentication对象，构造参数是Principal Credentials 与 Authorities
                    // 后面的拦截器里面会用到 grantedAuthorities 方法
                    UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(user, user.getPassword(), user.getAuthorities());

                    // 将authentication信息放入到上下文对象中
                    SecurityContextHolder.getContext().setAuthentication(authentication);

                    log.info("JWT过滤器通过校验请求头token自动登录成功, user : {}", user.getUsername());
                }
            }
        }
        // 未通过校验，继续执行下一个过滤器
        chain.doFilter(request, response);
    }
}
