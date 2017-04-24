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
import org.springframework.stereotype.Controller;
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
@Controller
@RequestMapping("/wechat")
public class WechatController extends BasicController {

    @Value("${wechat.default.url}")
    private String defaultUrl;
    @Value("${wechat.bind.url}")
    private String bindUrl;


    @Autowired
    private WechatRepository wechatRepository;
    @Autowired
    MemberRepository memberRepository;

    @Value("${wechat.appid}")
    private String appid;
    @Value("${wechat.appseceret}")
    private String appSeceret;

    final private String acessTokenUrl =
            "https://api.weixin.qq.com/sns/oauth2/access_token?" +
                    "appid=%s&secret=%s&code=%s&grant_type=authorization_code";


    Logger logger = LoggerFactory.getLogger(this.getClass());







    @RequestMapping("/grant")
    String bind(HttpSession session,
                      @RequestParam("code") String code,
                      @RequestParam("state") String state) throws Exception {


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
            return "redirect:" + defaultUrl;

        }
        Integer userId = (Integer) session.getAttribute("userId");
        if (userId == null) {
            session.setAttribute("openid",accessTokenDTO.getOpenid());
            return "redirect:" + bindUrl;
        }
        weChatDO = new WeChatDO();
        weChatDO.setAppId(appid);
        weChatDO.setId(userId);
        weChatDO.setOpenId(accessTokenDTO.getOpenid());
        wechatRepository.save(weChatDO);
        return "redirect:" + defaultUrl;
        //return actionResult(ErrorCode.Success);
    }





}
