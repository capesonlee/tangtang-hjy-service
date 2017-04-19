package com.lijuyong.startup.web.infra.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.youbang.infrastructure.web.HttpUtil;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;

import org.springframework.security.web.util.matcher.AntPathRequestMatcher;


import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by john on 2017/4/18.
 */

/**
 * 主要用来修改验证信息时如何在消息体重解析用户名和密码
* */
public class RestAuthenticationFilter extends AbstractAuthenticationProcessingFilter {

    public RestAuthenticationFilter(String urlMapping, AuthenticationManager authManager) {
        super(new AntPathRequestMatcher(urlMapping,"POST"));
        setAuthenticationManager(authManager);
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request,
                                                HttpServletResponse response)
            throws AuthenticationException, IOException, ServletException {

        String username;
        String password;
        if( "application/json".equals(request.getHeader("content-type") )){
            final LoinUserReq user =
                    new ObjectMapper().readValue(request.getInputStream(), LoinUserReq.class);
            username = user.getUsername();
            password = user.getPassword();

        }
        else
        {
            username = request.getParameter("username");
            password = request.getParameter("password");
        }

        final UsernamePasswordAuthenticationToken loginToken =
                new UsernamePasswordAuthenticationToken(
                        username, password);

        return getAuthenticationManager().authenticate(loginToken);
    }
}


