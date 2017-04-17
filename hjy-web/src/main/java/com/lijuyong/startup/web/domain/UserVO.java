package com.lijuyong.startup.web.domain;

/**
 * Created by john on 2017/4/17.
 */
public class UserVO {
    private String loginName;
    private String password;

    public String getPassword() {
        return password;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public void setPassword(String password) {
        password = password;
    }
}
