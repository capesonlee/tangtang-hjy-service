package com.lijuyong.startup.web;

import com.lijuyong.startup.entity.MemberDO;
import com.lijuyong.startup.entity.WeChatDO;
import com.lijuyong.startup.repository.MemberRepository;
import com.lijuyong.startup.repository.WechatRepository;
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
        RevenueVO revenueVO = new RevenueVO();
        Integer userId = (Integer) session.getAttribute("userId");
        if (userId == null) {
            return actionResult(ErrorCode.NeedAuthenticated);
        }
        MemberDO memberDO = memberRepository.findOne(userId);
        revenueVO.setBonus(memberDO.getBonus());
        revenueVO.setScore(memberDO.getScore());
        revenueVO.setWithdraw(memberDO.getWithdraw());
        revenueVO.setAnuualRate(0.108);
        revenueVO.setDailyRevenue(
                memberDO.getQuantity()
                        *memberDO.getCost()
                        *revenueVO.getAnuualRate()/365.0);

        Date today =  new Date();
        Calendar rightNow = Calendar.getInstance();
        long days = rightNow.get(Calendar.DAY_OF_MONTH);
        revenueVO.setMonthlyRevenue(revenueVO.getDailyRevenue()*days);

        revenueVO.setTotalRevenue(revenueVO.getWithdraw() + revenueVO.getBonus());
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
        String openid = (String)session.getAttribute("openid");
        if( openid == null){
            return actionResult(ErrorCode.Success);
        }
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
