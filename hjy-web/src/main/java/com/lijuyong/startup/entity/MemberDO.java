package com.lijuyong.startup.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Created by john on 2017/4/16.
 */
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
