package com.lijuyong.startup.web.infra.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

/**
 * Created by john on 2017/4/18.
 */
public class LocalAuthUser implements UserDetails {
    private final String username;
    private final String password;
    private final Integer userId;
    private  String companyName;
    private  String companyCode;

    public LocalAuthUser( String username,String password,Integer userId) {

        this.username = username;
        this.password = password;
        this.userId = userId;

    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public void setCompanyCode(String companyCode) {
        this.companyCode = companyCode;
    }

    public String getCompanyName() {
        return companyName;
    }

    public String getCompanyCode() {
        return companyCode;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public Integer getUserId() {
        return userId;
    }
}
