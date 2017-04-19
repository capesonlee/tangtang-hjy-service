package com.lijuyong.startup.web.infra.security;

import com.youbang.infrastructure.log.ErrorCode;
import com.youbang.infrastructure.web.ActionResult;
import com.youbang.infrastructure.web.HttpUtil;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by john on 2017/4/19.
 */
public class RestAuthenticationFailureHandler implements AuthenticationFailureHandler {
    @Override
    public void onAuthenticationFailure(HttpServletRequest request,
                                        HttpServletResponse response,
                                        AuthenticationException exception)
            throws IOException, ServletException {

        ActionResult actionResult =
                new ActionResult<String>(ErrorCode.AuthenticationFailed.getCode(),
                        ErrorCode.AuthenticationFailed.getDesc(),
                        exception.getMessage());
        HttpUtil.addJsonBodyToResponse(response,actionResult);


    }
}
