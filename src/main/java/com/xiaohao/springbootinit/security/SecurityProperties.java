package com.xiaohao.springbootinit.security;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "security.jwt")
@Data
public class SecurityProperties {
    /**
     * 管理端员工生成jwt令牌相关配置
     */
    private String secretKey;
    private Long ttl;
    private String tokenName;
    private String claimsUsername;
    private String claimsRole;
    private String tokenPrefix;


}
