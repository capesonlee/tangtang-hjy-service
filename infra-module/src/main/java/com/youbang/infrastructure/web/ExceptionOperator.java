package com.youbang.infrastructure.web;

import com.youbang.infrastructure.log.ErrorCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.NativeWebRequest;

/**
 * Created by john on 2017/4/12.
 */

public class ExceptionOperator {
    protected Logger logger = LoggerFactory.getLogger(this.getClass());
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public ActionResult processExcption(NativeWebRequest request, Exception e){
        System.out.println(e.getMessage());
        logger.error("未知的异常",e);
        ErrorCode code = ErrorCode.UnexpectedFailure;
        return  new ActionResult(code.getCode(),e.getMessage(),null);
    }
    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseBody
    public ActionResult processIllegalArumentExcption(NativeWebRequest request, Exception e){
        System.out.println(e.getMessage());
        logger.error("已知的异常",e);
        ErrorCode code = ErrorCode.UnexpectedFailure;
        return  new ActionResult(code.getCode(),e.getMessage(),null);
    }



}
