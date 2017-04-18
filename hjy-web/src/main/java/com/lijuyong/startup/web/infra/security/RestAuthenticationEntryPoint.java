package com.lijuyong.startup.web.infra.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.youbang.infrastructure.log.ErrorCode;
import com.youbang.infrastructure.web.ActionResult;
import com.youbang.infrastructure.web.HttpUtil;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;

/**
 * Created by john on 2017/3/5.
 */
@Service
public class RestAuthenticationEntryPoint implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request,
                         HttpServletResponse response,
                         AuthenticationException authExcption) throws IOException {

        ActionResult actionResult =
                new ActionResult(ErrorCode.NeedAuthenticated.getCode(),
                        ErrorCode.NeedAuthenticated.getDesc(),
                        null);
        HttpUtil.addJsonBodyToResponse(response,actionResult);

    }

}
