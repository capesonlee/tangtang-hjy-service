package com.youbang.infrastructure.log;

import java.rmi.UnexpectedException;

/**
 * Created by huaxiulin on 2017/4/11.
 */
public enum ErrorCode
{
    Success(0,"成功"),
    Failure(1,"操作失败"),
    UnexpectedFailure(2,"未知的异常"),
    NotFound(3,"数据不存在"),
    LoginNameExists(4,"用户名已经存在"),
    DataExists(5,"数据已经存在"),
    NeedAuthenticated(6,"需要登录"),
    AuthenticationFailed(7,"用户名不存在或者密码错误"),
    NeedBindWechat(8,"需要绑定用户名密码");



    private String desc;
    private int code;

    private ErrorCode(int code, String desc)
    {
        this.desc = desc;
        this.code = code;
    }

    public String getDesc()
    {
        return desc;
    }

    public int getCode()
    {
        return code;
    }
}
