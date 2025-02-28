package com.xiaohao.springbootinit.model.dto.user;

import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * 用户注册请求体
 */
@Data
public class UserRegisterRequest implements Serializable {

    private static final long serialVersionUID = 3191241716373120793L;

    @NotBlank(message = "用户账号不能为空")
    @Size(min = 4, message = "用户名长度不能小于4")
    private String userAccount;

    @NotBlank(message = "用户密码不能为空")
    @Size(min = 4, message = "密码长度不能小于4")
    private String userPassword;

    @NotBlank(message = "确认密码不能为空")
    @Size(min = 4, message = "确认密码长度不能小于4")
    private String checkPassword;
}
