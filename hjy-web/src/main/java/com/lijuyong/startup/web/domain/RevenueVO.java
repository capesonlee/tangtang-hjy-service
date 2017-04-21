package com.lijuyong.startup.web.domain;

import com.lijuyong.startup.entity.MemberDO;

import javax.persistence.Column;

/**
 * Created by john on 2017/4/21.
 */
public class RevenueVO {

    //    昨日收益，
    private Double dailyRevenue;
    //    当月累计收益，
    private Double monthlyRevenue;
    //    总收益，
    private Double totalRevenue;
    //    年化收益率，
    private Double anuualRate;
    //    积分，
    private Integer score;
    //    业绩奖励，
    private Double bonus;
    //    盈亏体现，
    private Double withdraw;
    //    投资收益率，
    private Double revenueRate;
    //    总提现金额
    private Double totalWithdraw;



    public void setScore(Integer score) {
        this.score = score;
    }

    public Double getAnuualRate() {
        return anuualRate;
    }

    public Double getBonus() {
        return bonus;
    }

    public Double getDailyRevenue() {
        return dailyRevenue;
    }

    public Double getMonthlyRevenue() {
        return monthlyRevenue;
    }

    public Double getRevenueRate() {
        return revenueRate;
    }

    public Double getTotalRevenue() {
        return totalRevenue;
    }

    public Double getTotalWithdraw() {
        return totalWithdraw;
    }

    public Double getWithdraw() {
        return withdraw;
    }

    public Integer getScore() {
        return score;
    }

    public void setAnuualRate(Double anuualRate) {
        this.anuualRate = anuualRate;
    }

    public void setBonus(Double bonus) {
        this.bonus = bonus;
    }

    public void setDailyRevenue(Double dailyRevenue) {
        this.dailyRevenue = dailyRevenue;
    }

    public void setMonthlyRevenue(Double monthlyRevenue) {
        this.monthlyRevenue = monthlyRevenue;
    }

    public void setRevenueRate(Double revenueRate) {
        this.revenueRate = revenueRate;
    }

    public void setTotalRevenue(Double totalRevenue) {
        this.totalRevenue = totalRevenue;
    }

    public void setTotalWithdraw(Double totalWithdraw) {
        this.totalWithdraw = totalWithdraw;
    }

    public void setWithdraw(Double withdraw) {
        this.withdraw = withdraw;
    }
}
