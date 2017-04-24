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


    public RevenueVO getRevenue(Integer userId) {
        MemberDO memberDO = memberRepository.findOne(userId);
        RevenueVO revenueVO = new RevenueVO();
        //业绩奖励
        revenueVO.setBonus(memberDO.getBonus());
        //积分
        revenueVO.setScore(memberDO.getScore());
        //盈亏提现
        revenueVO.setWithdraw(memberDO.getWithdraw());

        //投资金额
        revenueVO.setInvestValue(memberDO.getQuantity() * memberDO.getCost());
        //当前价值
        revenueVO.setCurentValue(memberDO.getQuantity() * memberDO.getCurrentPrice());

        //盈亏
        revenueVO.setWinLose(revenueVO.getCurentValue() - revenueVO.getInvestValue());
        //盈亏率
        revenueVO.setWinLoseRate(revenueVO.getWinLose() / revenueVO.getInvestValue());


        //年华收益率
        revenueVO.setAnuualRate(memberDO.getAnnualRate());


        //今日收益
        revenueVO.setDailyRevenue(
                memberDO.getQuantity()
                        * memberDO.getCost()
                        * revenueVO.getAnuualRate() / 365.0);
        //当月每天收益率累计
        Date today = new Date();
        Calendar rightNow = Calendar.getInstance();
        long days = rightNow.get(Calendar.DAY_OF_MONTH);
        revenueVO.setMonthlyRevenue(revenueVO.getDailyRevenue() * days);

        //分红计算

        Date purchaseDate = memberDO.getPurchaseDate();
        long currentTime = rightNow.getTimeInMillis();
        rightNow.setTime(purchaseDate);
        long purchaseTime = rightNow.getTimeInMillis();

        long totalDays = (currentTime - purchaseTime) / (1000 * 3600 * 24);
        //分红
        double dividen = revenueVO.getDailyRevenue() * totalDays;
        //总收益：TODO
        revenueVO.setTotalRevenue(dividen + memberDO.getBonus() + revenueVO.getWinLose());

        //总提现额
        revenueVO.setTotalWithdraw(
                revenueVO.getTotalRevenue()
                        - revenueVO.getWinLose() + revenueVO.getWithdraw());

        //投资收益率
        revenueVO.setRevenueRate(revenueVO.getTotalRevenue() / revenueVO.getInvestValue());


        return revenueVO;
    }
}
