package com.xiaohao.springbootinit.security;



import cn.hutool.json.JSONUtil;
import com.xiaohao.springbootinit.common.BaseResponse;
import com.xiaohao.springbootinit.common.ResultUtils;
import org.apache.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @description 未认证处理逻辑,告诉用户权限不够就可以了
 * @date 2023/4/23 20:23
 */

@Component
public class UnAuthenticationEntryPointHandler implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        response.setContentType("application/json;charset=utf-8");
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        BaseResponse result = ResultUtils.error(HttpStatus.SC_PROXY_AUTHENTICATION_REQUIRED,"请先登录");
        response.getWriter().write(JSONUtil.toJsonStr(result));
    }
}
