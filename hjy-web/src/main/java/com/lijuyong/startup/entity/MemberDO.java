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
//        买入数量，
//        购买成本，
//        买入日期，
//        最新价格，
//        转让日期，
//        回购日期，
//        最低回购价，
//        年收益率，
//        积分，
//        业绩奖励，
//        盈亏提现
@Entity(name = "t_member")
public class MemberDO {
    @Id
    @GeneratedValue
    private  Integer id;
    @Column(name="user_name")
    private String name;

    @Column(name = "login_name",unique = true)
    private String loginName;

    @Column(name = "login_password")
    private String loginPassword;

    @Column(name="grade")
    private Integer grade;

    @Column(name = "purchase_quantity")
    private Double quantity;

    @Column(name ="purchase_cost")
    private Double cost;

    @Column(name = "purchase_date")
    private Timestamp purchaseDate;

    @Column(name = "sellout_date")
    private Timestamp selloutDate;

    @Column(name = "buyback_date")
    private Timestamp buybackDate;

    @Column(name ="buyback_price" )
    private Double buybackPrice;

    @Column(name = "anuual_rate")
    private Double anuualRate;

    @Column(name ="score")
    private Integer score;

    @Column(name="bonus")
    private Double bonus;

    @Column(name = "withdraw")
    private Double withdraw;

    public Double getAnuualRate() {
        return anuualRate;
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

    public void setAnuualRate(Double anuualRate) {
        this.anuualRate = anuualRate;
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
