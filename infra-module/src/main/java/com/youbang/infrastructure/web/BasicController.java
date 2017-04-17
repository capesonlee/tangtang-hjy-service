package com.youbang.infrastructure.web;

import com.youbang.infrastructure.log.ErrorCode;

/**
 * Created by john on 2017/4/11.
 */
public class BasicController {
    public <T> ActionResult<T> actionResult(ErrorCode code, T value){
        return  new ActionResult<T>(code.getCode(),
                code.getDesc(),
                value);
    }
    public <T> ActionResult<T> actionResult(T value){
        ErrorCode code = ErrorCode.Success;
        return  actionResult(code,value);
    }


}
