package com.lijuyong.startup.web.infra.security;

/**
 * Created by john on 2017/4/18.
 */
public class LoinUserReq {
    private String username;
    private String password;

    public String getPassword() {
        return password;
    }

    public String getUsername() {
        return username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
