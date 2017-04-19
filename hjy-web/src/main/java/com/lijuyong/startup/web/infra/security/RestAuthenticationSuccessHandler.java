package com.lijuyong.startup.web.infra.security;

import com.youbang.infrastructure.log.ErrorCode;
import com.youbang.infrastructure.web.ActionResult;
import com.youbang.infrastructure.web.HttpUtil;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by john on 2017/3/5.
 */
public class RestAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
                                        HttpServletResponse response,
                                        Authentication authentication ){
        ActionResult actionResult =
                new ActionResult(ErrorCode.Success.getCode(),
                        ErrorCode.Success.getDesc(),
                        null);
        HttpUtil.addJsonBodyToResponse(response,actionResult);


    }
}
