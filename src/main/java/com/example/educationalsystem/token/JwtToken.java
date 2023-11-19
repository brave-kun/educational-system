package com.example.educationalsystem.token;

import org.apache.shiro.authc.AuthenticationToken;

/**
 * 功能：
 * 作者： bravekun
 * 日期： 2023/11/18 22:01
 */

public class JwtToken implements AuthenticationToken {
    private String token;

    public JwtToken(String token) {
        this.token = token;
    }

    //返回原来的字符串，解析交给JwtUtils实现
    @Override
    public Object getPrincipal() {
        return token;
    }

    //返回原来的字符串，解析交给JwtUtils实现
    @Override
    public Object getCredentials() {
        return token;
    }
}
