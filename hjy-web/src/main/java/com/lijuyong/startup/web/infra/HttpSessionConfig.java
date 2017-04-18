package com.lijuyong.startup.web.infra;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.session.jdbc.config.annotation.web.http.EnableJdbcHttpSession;
import org.springframework.session.web.http.CookieHttpSessionStrategy;
import org.springframework.session.web.http.HttpSessionStrategy;

/**
 * Created by john on 2017/4/17.
 */
//@Configuration
//public class HttpSessionConfig {
//    @Bean
//    public HttpSessionStrategy httpSessionStrategy() {
//        return new CookieHttpSessionStrategy();
//    }
//}
