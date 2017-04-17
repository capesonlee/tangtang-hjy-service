package com.lijuyong.startup.web;

import com.lijuyong.startup.entity.MemberDO;
import com.lijuyong.startup.repository.MemberRepository;
import com.lijuyong.startup.repository.WeChatRepository;


import com.youbang.infrastructure.log.ErrorCode;
import com.youbang.infrastructure.web.ActionResult;
import com.youbang.infrastructure.web.BasicController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * Created by john on 2017/4/16.
 */
@RestController
@RequestMapping("/member")
public class MemberController extends BasicController{
    @Autowired
    MemberRepository memberRepository;

    @Autowired
    WeChatRepository weChatRepository;

    @RequestMapping("/test")
    public String test(){
        return "this is a test";
    }

    @RequestMapping(value = "/save",method = RequestMethod.POST)
    public ActionResult<MemberDO> save(@RequestBody MemberDO memberDO){
        memberRepository.save(memberDO);
        return actionResult(memberDO);
    }



    @RequestMapping(value = "/remove",method = RequestMethod.POST)
    public ActionResult<Integer> remove(@RequestParam("id") Integer id){
        memberRepository.delete(id);
        return  actionResult(id);
    }

    @RequestMapping(value = "/show",method = RequestMethod.GET)
    public ActionResult show(@RequestParam("id") Integer id){
        MemberDO memberDO = memberRepository.findOne(id);
        if(memberDO == null){
            return actionResult(ErrorCode.NotFound,id);
        }
        return  actionResult(memberDO);
    }

    @RequestMapping(value = "/list",method = RequestMethod.POST)
    public ActionResult list(@RequestParam("page") Integer page,@RequestParam("size") Integer size){
        if( page<=0){
            page =1;
        }

        Sort sort = new Sort(new Sort.Order(Sort.Direction.DESC, "name"));
        Pageable pageable = new PageRequest(page-1,size,sort);

        Page<MemberDO> memberList = memberRepository.findAll(pageable);
        return  actionResult(memberList);
    }

    @RequestMapping(value = "/loginname",method = RequestMethod.GET)
    public ActionResult validateLoginName(@RequestParam("value") String loginName){
        MemberDO x = memberRepository.findByLoginName(loginName);
        MemberDO y = memberRepository.getByLoginName(loginName);
        if( x != null ){
            return actionResult(x);
        }
        System.out.println("here is login name " + loginName);
        if( y == null ){
            return actionResult(ErrorCode.Success);
        }
        return actionResult(ErrorCode.LoginNameExists,y);
    }


}
