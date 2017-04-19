package com.lijuyong.startup.web;



import com.lijuyong.startup.entity.MemberDO;
import com.lijuyong.startup.web.infra.security.LocalAuthUser;
import com.lijuyong.startup.repository.MemberRepository;
import com.youbang.infrastructure.log.ErrorCode;
import com.youbang.infrastructure.web.ActionResult;
import com.youbang.infrastructure.web.BasicController;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;


/**
 * Created by john on 2017/4/17.
 */
@RestController
@RequestMapping("/user")
public class UserController extends BasicController{


    @Autowired
    MemberRepository memberRepository;
    @RequestMapping("/detail")
    ActionResult detail(HttpSession session){
        //Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        //LocalAuthUser localAuthUser = (LocalAuthUser)principal;

        String openid = (String)session.getAttribute("openid");
        if( openid == null){
            return  actionResult(ErrorCode.NeedBindWechat);
        }

        MemberDO memberDO = memberRepository.findOne(1);
        return  actionResult(ErrorCode.Success,openid);
    }

    @RequestMapping("/bind")
    ActionResult bind(HttpSession session){
        session.setAttribute("openid","abcd123556");
        String openid = (String)session.getAttribute("openid");
        return  actionResult(ErrorCode.Success,openid);
    }

    @RequestMapping("/logout")
    ActionResult logout(HttpSession session){
        session.invalidate();
        return actionResult(ErrorCode.Success);
    }




}
