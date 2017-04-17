package com.lijuyong.startup.web.infra;

import com.youbang.infrastructure.log.ErrorCode;
import com.youbang.infrastructure.web.ActionResult;
import com.youbang.infrastructure.web.ExceptionOperator;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.NativeWebRequest;

/**
 * Created by john on 2017/4/12.
 */
@ControllerAdvice
public class ExceptionAdvice extends ExceptionOperator {
    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseBody
    public ActionResult processonstraintViloationException (NativeWebRequest request, Exception e){
        System.out.println(e.getMessage());
        logger.error("已知的异常",e);
        ErrorCode code = ErrorCode.DataExists;
        return  new ActionResult(code.getCode(),code.getDesc(),null);
    }
}
