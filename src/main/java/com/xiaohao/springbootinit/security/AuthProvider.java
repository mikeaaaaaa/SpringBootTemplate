package com.xiaohao.springbootinit.security;

import com.xiaohao.springbootinit.model.entity.User;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.List;

public class AuthProvider {
    public static Authentication getAuth() {
        return SecurityContextHolder.getContext().getAuthentication();
    }

    public static User getUser() {
        if (getAuth() == null) {
            return null;
        }
        return (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

    public static String getLoginAccount() {
        if (getAuth() == null) {
            return null;
        }
        return ((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUserAccount();
    }

    public static Long getUserId() {
        if (getAuth() == null) {
            return null;
        }
        return ((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getId();
    }

    public static String getAuthorities() {
        if (getAuth() == null) {
            return null;
        }
        return ((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUserRole();
    }
}
