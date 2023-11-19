package com.example.educationalsystem.filter;

import com.example.educationalsystem.token.JwtToken;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.shiro.web.filter.authc.BasicHttpAuthenticationFilter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 * 功能： Jwt过滤器
 * 作者： bravekun
 * 日期： 2023/11/18 22:21
 */

public class JwtFilter extends BasicHttpAuthenticationFilter {
    //private static final String TOKEN = "Authentication";

    /**
     * 判断用户是否想要登入。
     * 检测header里面是否包含token字段即可
     */
//    @Override
//    protected boolean isLoginAttempt(ServletRequest request, ServletResponse response) {
//        HttpServletRequest req = (HttpServletRequest) request;
//        String authorization = req.getHeader("token");
//        return authorization != null;
//    }
    @Override
    protected boolean isLoginAttempt(javax.servlet.ServletRequest request, javax.servlet.ServletResponse response) {
        HttpServletRequest req = (HttpServletRequest) request;
        String authorization = req.getHeader("token");
        return authorization != null;
    }

    /**
     *
     */
//    @Override
//    protected boolean executeLogin(ServletRequest request, ServletResponse response) throws Exception {
//        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
//        String authorization = httpServletRequest.getHeader("token");
//
//        JwtToken token = new JwtToken(authorization);
//        // 提交给realm进行登入，如果错误他会抛出异常并被捕获
//        getSubject(request, response).login(token);
//        // 如果没有抛出异常则代表登入成功，返回true
//        return true;
//    }
    @Override
    protected boolean executeLogin(javax.servlet.ServletRequest request, javax.servlet.ServletResponse response)  {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        String authorization = httpServletRequest.getHeader("token");

        JwtToken token = new JwtToken(authorization);
        // 提交给realm进行登入，如果错误他会抛出异常并被捕获
        getSubject(request, response).login(token);

        // 如果没有抛出异常则代表登入成功，返回true
        return true;
    }

    /**
     * 这里我们详细说明下为什么最终返回的都是true，即允许访问
     * 例如我们提供一个地址 GET /article
     * 登入用户和游客看到的内容是不同的
     * 如果在这里返回了false，请求会被直接拦截，用户看不到任何东西
     * 所以我们在这里返回true，Controller中可以通过 subject.isAuthenticated() 来判断用户是否登入
     * 如果有些资源只有登入用户才能访问，我们只需要在方法上面加上 @RequiresAuthentication 注解即可
     * 但是这样做有一个缺点，就是不能够对GET,POST等请求进行分别过滤鉴权(因为我们重写了官方的方法)，但实际上对应用影响不大
     */
//    @Override
//    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {
//        if (isLoginAttempt(request, response)) {
//            executeLogin(request, response);
//        }
//        return true;
//    }
    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue){
        //return super.isAccessAllowed(request, response, mappedValue);
        if (isLoginAttempt(request, response)) {
            executeLogin(request, response);
            return true;
        }else {
            return false;
        }

    }

}
