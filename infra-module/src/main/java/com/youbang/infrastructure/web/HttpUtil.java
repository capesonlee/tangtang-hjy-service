package com.youbang.infrastructure.web;

import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

/**
 * Created by john on 2017/4/18.
 */
public class HttpUtil {
    static public <T> boolean addJsonBodyToResponse(HttpServletResponse response, T value){

        try{
            ObjectMapper objectMapper = new ObjectMapper();
            String json = objectMapper.writeValueAsString(value);
            response.addHeader("content-type", "application/json;charset=utf-8");
            response.setCharacterEncoding("UTF-8");
            PrintWriter printWriter = response.getWriter();
            printWriter.write(json);
            printWriter.flush();
            printWriter.close();

        }
        catch (Exception e){
            System.out.println(e.getMessage());
            return false;
        }
        return  true;

    }

}
