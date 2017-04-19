package com.lijuyong.startup.web.infra;

import com.lijuyong.startup.web.infra.security.RestAuthenticationEntryPoint;
import com.lijuyong.startup.web.infra.security.RestAuthenticationFailureHandler;
import com.lijuyong.startup.web.infra.security.RestAuthenticationFilter;
import com.lijuyong.startup.web.infra.security.RestAuthenticationSuccessHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * Created by john on 2017/2/15.
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private RestAuthenticationEntryPoint restAuthenticationEntryPoint;
    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    public void configureAuthentication(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
        authenticationManagerBuilder
                .userDetailsService(this.userDetailsService);
//                .passwordEncoder(passwordEncoder());
    }
    @Bean
    public RestAuthenticationFilter restAuthenticationFilterBean() throws Exception {
        RestAuthenticationFilter restAuthenticationFilter = new RestAuthenticationFilter("/auth/signin",
                authenticationManager());
        restAuthenticationFilter.setAuthenticationSuccessHandler(new RestAuthenticationSuccessHandler());
        restAuthenticationFilter.setAuthenticationFailureHandler(new RestAuthenticationFailureHandler());
        return restAuthenticationFilter;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .exceptionHandling()
                .authenticationEntryPoint(restAuthenticationEntryPoint)
                .and()
                .authorizeRequests()
                .antMatchers("/*.html").permitAll()
                .antMatchers("/member/validateName").permitAll()
                .anyRequest().authenticated()
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED);

        http.addFilterBefore(restAuthenticationFilterBean(),
                UsernamePasswordAuthenticationFilter.class);
    }


}
