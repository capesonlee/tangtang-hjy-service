package com.lijuyong.startup.web;

import com.lijuyong.startup.entity.MemberDO;
import com.lijuyong.startup.entity.WeChatDO;
import com.lijuyong.startup.repository.MemberRepository;
import com.lijuyong.startup.repository.WechatRepository;
import com.lijuyong.startup.service.RevenueService;
import com.lijuyong.startup.web.domain.RevenueVO;
import com.lijuyong.startup.web.domain.UserVO;
import com.youbang.infrastructure.log.ErrorCode;
import com.youbang.infrastructure.web.ActionResult;
import com.youbang.infrastructure.web.BasicController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by john on 2017/4/23.
 */
@RestController
@RequestMapping("/user")
public class UserController extends BasicController {

    @Autowired
    private WechatRepository wechatRepository;
    @Autowired
    MemberRepository memberRepository;

    @Autowired
    RevenueService revenueService;

    @RequestMapping("/detail")
    ActionResult detail(HttpSession session) {
        Integer userId = (Integer) session.getAttribute("userId");
        if (userId == null) {
            return actionResult(ErrorCode.NeedAuthenticated);
        }
        MemberDO memberDO = memberRepository.findOne(userId);
        return actionResult(ErrorCode.Success, memberDO);
    }
    @RequestMapping("/revenue")
    ActionResult revenue(HttpSession session){

        Integer userId = (Integer) session.getAttribute("userId");
        if (userId == null) {
            return actionResult(ErrorCode.NeedAuthenticated);
        }
        RevenueVO revenueVO = revenueService.getRevenue(userId);
        return  actionResult(revenueVO);
    }

    protected ActionResult userLogin(UserVO userVO, HttpSession session) {
        MemberDO memberDO = memberRepository.findByLoginName(userVO.getLoginName());
        if (memberDO == null) {
            return actionResult(ErrorCode.AuthenticationFailed);
        }
        boolean valide = memberDO.getLoginPassword().equals(userVO.getPassword());
        if (!valide) {
            return actionResult(ErrorCode.AuthenticationFailed);
        }
        session.setAttribute("userId", memberDO.getId());
     
        return actionResult(ErrorCode.Success);

    }



    @RequestMapping("/login")
    ActionResult login(UserVO userVO, HttpSession session) {
        return userLogin(userVO, session);

    }



    @RequestMapping("/signin")
    ActionResult signin(@RequestBody UserVO userVO, HttpSession session) {
        return userLogin(userVO, session);
    }
    @RequestMapping("/logout")
    ActionResult logout(HttpSession session) {
        session.invalidate();
        return actionResult(ErrorCode.Success);
    }
}
