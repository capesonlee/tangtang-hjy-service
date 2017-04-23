package com.lijuyong.startup.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.sql.Timestamp;

/**
 * Created by john on 2017/4/16.
 */
//
//会员姓名，
//        手机号码，
//        类型id,（id为0表示劣后级，id为1表示优先级）
//        类型名称，

@Entity(name = "t_member")
public class MemberDO {
    @Id
    @GeneratedValue
    private  Integer id;
    @Column(name="user_name")
    private String name;

    //账号
    @Column(name = "login_name",unique = true)
    private String loginName;

    //密码
    @Column(name = "login_password")
    private String loginPassword;

    //类型id
    @Column(name="grade")
    private Integer grade;

    //        买入数量，
    @Column(name = "purchase_quantity")
    private Double quantity;

    //        购买成本，
    @Column(name ="purchase_cost")
    private Double cost;

    //        买入日期，
    @Column(name = "purchase_date")
    private Timestamp purchaseDate;

    //        转让日期，
    @Column(name = "sellout_date")
    private Timestamp selloutDate;

    //        回购日期，
    @Column(name = "buyback_date")
    private Timestamp buybackDate;

    //        最低回购价，
    @Column(name ="buyback_price" )
    private Double buybackPrice;

    //        年收益率，
    @Column(name = "annual_rate")
    private Double annualRate;

    //        积分，
    @Column(name ="score")
    private Integer score;

    //        业绩奖励，
    @Column(name="bonus")
    private Double bonus;

    //        盈亏提现
    @Column(name = "withdraw")
    private Double withdraw;

    // 最新价格
    @Column(name = "current_price")
    private Double  currentPrice;

    public Double getCurrentPrice() {
        return currentPrice;
    }

    public void setCurrentPrice(Double currentPrice) {
        this.currentPrice = currentPrice;
    }

    public Double getAnnualRate() {
        return annualRate;
    }

    public Double getBonus() {
        return bonus;
    }

    public Double getBuybackPrice() {
        return buybackPrice;
    }

    public Double getWithdraw() {
        return withdraw;
    }

    public Integer getScore() {
        return score;
    }

    public Timestamp getBuybackDate() {
        return buybackDate;
    }

    public Timestamp getPurchaseDate() {
        return purchaseDate;
    }

    public Timestamp getSelloutDate() {
        return selloutDate;
    }

    public void setAnnualRate(Double annualRate) {
        this.annualRate = annualRate;
    }

    public void setBonus(Double bonus) {
        this.bonus = bonus;
    }

    public void setBuybackDate(Timestamp buybackDate) {
        this.buybackDate = buybackDate;
    }

    public void setPurchaseDate(Timestamp purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    public void setSelloutDate(Timestamp selloutDate) {
        this.selloutDate = selloutDate;
    }

    public void setBuybackPrice(Double buybackPrice) {
        this.buybackPrice = buybackPrice;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public void setWithdraw(Double withdraw) {
        this.withdraw = withdraw;
    }

    public Double getCost() {
        return cost;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setQuantity(Double quantity) {
        this.quantity = quantity;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getQuantity() {
        return quantity;
    }

    public Integer getGrade() {
        return grade;
    }

    public String getName() {
        return name;
    }

    public void setCost(Double cost) {
        this.cost = cost;
    }

    public void setGrade(Integer grade) {
        this.grade = grade;
    }

    public String getLoginName() {
        return loginName;
    }

    public String getLoginPassword() {
        return loginPassword;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public void setLoginPassword(String loginPassword) {
        this.loginPassword = loginPassword;
    }
    public MemberDO(){
        super();
    }
}
