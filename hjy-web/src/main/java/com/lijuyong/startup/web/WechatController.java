package com.lijuyong.startup.web;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.lijuyong.startup.entity.MemberDO;
import com.lijuyong.startup.entity.WeChatDO;
import com.lijuyong.startup.repository.WechatRepository;
import com.lijuyong.startup.web.domain.AccessTokenDTO;
import com.lijuyong.startup.repository.MemberRepository;
import com.lijuyong.startup.web.domain.RevenueVO;
import com.lijuyong.startup.web.domain.UserVO;
import com.lijuyong.startup.web.infra.security.LocalAuthUser;
import com.youbang.infrastructure.log.ErrorCode;
import com.youbang.infrastructure.web.ActionResult;
import com.youbang.infrastructure.web.BasicController;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpSession;

import java.util.Calendar;
import java.util.Date;

import static com.fasterxml.jackson.databind.DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES;


/**
 * Created by john on 2017/4/17.
 */
@RestController
@RequestMapping("/wechat")
public class WechatController extends BasicController {


    @Autowired
    private WechatRepository wechatRepository;

    @Value("${wechat.appid}")
    private String appid;
    @Value("${wechat.appseceret}")
    private String appSeceret;

    final private String acessTokenUrl =
            "https://api.weixin.qq.com/sns/oauth2/access_token?" +
                    "appid=%s&secret=%s&code=%s&grant_type=authorization_code";


    Logger logger = LoggerFactory.getLogger(this.getClass());


    @Autowired
    MemberRepository memberRepository;

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

    @RequestMapping("/detail")
    ActionResult detail(HttpSession session) {
        Integer userId = (Integer) session.getAttribute("userId");
        if (userId == null) {
            return actionResult(ErrorCode.NeedAuthenticated);
        }
        MemberDO memberDO = memberRepository.findOne(userId);
        return actionResult(ErrorCode.Success, memberDO);
    }

    @RequestMapping("/login")
    ActionResult login(UserVO userVO, HttpSession session) {
        return userLogin(userVO, session);

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

    @RequestMapping("/signin")
    ActionResult signin(@RequestBody UserVO userVO, HttpSession session) {
        return userLogin(userVO, session);
    }

    @RequestMapping("/grant")
    ActionResult bind(HttpSession session,
                      @RequestParam("code") String code,
                      @RequestParam("state") Integer state) throws Exception {


        AccessTokenDTO accessTokenDTO = null;
        RestTemplate restTemplate = new RestTemplate();

        String url = String.format(acessTokenUrl, appid, appSeceret, code);
        String content = restTemplate.getForObject(url, String.class);
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(FAIL_ON_UNKNOWN_PROPERTIES, false);
        accessTokenDTO = objectMapper.readValue(content, AccessTokenDTO.class);
        WeChatDO weChatDO = wechatRepository.findByAppIdAndOpenId(appid,
                accessTokenDTO.getOpenid());

        if (weChatDO != null) {

            session.setAttribute("userId", weChatDO.getId());
            return actionResult(ErrorCode.Success);

        }
        Integer userId = (Integer) session.getAttribute("userId");
        if (userId == null) {
            return actionResult(ErrorCode.NeedBindWechat);
        }
        weChatDO = new WeChatDO();
        weChatDO.setAppId(appid);
        weChatDO.setId(userId);
        weChatDO.setOpenId(accessTokenDTO.getOpenid());
        wechatRepository.save(weChatDO);
        return actionResult(ErrorCode.Success);
    }


    @RequestMapping("/logout")
    ActionResult logout(HttpSession session) {
        session.invalidate();
        return actionResult(ErrorCode.Success);
    }


}
