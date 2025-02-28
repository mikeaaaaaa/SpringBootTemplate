package com.xiaohao.springbootinit.model.dto.user;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * 用户登录请求
 */
@Data
public class UserLoginRequest implements Serializable {

    private static final long serialVersionUID = 3191241716373120793L;
    @NotBlank(message = "用户账号不能为空")
    @Size(min = 4, message = "用户名长度不能小于4")
    private String userAccount;
    @NotBlank(message = "用户密码不能为空")
    @Size(min = 4, message = "用户密码长度不能小于4")
    private String userPassword;
}
