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

    @Column(name="grade")
    private Integer grade;

    @Column(name = "purchase_quantity")
    private Double quantity;

    @Column(name ="purchase_cost")
    private Double cost;
}
