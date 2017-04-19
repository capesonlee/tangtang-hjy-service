package com.lijuyong.startup.web;

import com.lijuyong.startup.entity.MemberDO;
import com.lijuyong.startup.repository.MemberRepository;
import com.lijuyong.startup.web.domain.UserVO;
import com.lijuyong.startup.web.infra.security.LocalAuthUser;
import com.youbang.infrastructure.log.ErrorCode;
import com.youbang.infrastructure.web.ActionResult;
import com.youbang.infrastructure.web.BasicController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
    ActionResult detail(){
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        LocalAuthUser localAuthUser = (LocalAuthUser)principal;
        MemberDO memberDO = memberRepository.findOne(localAuthUser.getUserId());
        return  actionResult(ErrorCode.Success,memberDO);
    }



}
