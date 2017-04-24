package com.lijuyong.startup.service;

import com.lijuyong.startup.entity.MemberDO;
import com.lijuyong.startup.repository.MemberRepository;
import com.lijuyong.startup.web.domain.RevenueVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by john on 2017/4/24.
 */
@Service
public class RevenueService {

    @Autowired
    MemberRepository memberRepository;


    public RevenueVO getRevenue(Integer userId){
        MemberDO memberDO = memberRepository.findOne(userId);
        RevenueVO revenueVO = new RevenueVO();
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
        return  revenueVO;
    }
}
