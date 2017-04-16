package com.lijuyong.startup.web;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by john on 2017/4/16.
 */
@RestController
@RequestMapping("/member")
public class MemberController {
    @RequestMapping("/test")
    public String test(){
        return "this is a test";
    }
}
