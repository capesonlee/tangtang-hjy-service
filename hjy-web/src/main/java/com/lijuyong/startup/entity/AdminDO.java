package com.lijuyong.startup.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Created by john on 2017/4/19.
 */
@Entity(name = "t_admin")
public class AdminDO {

    @Id
    @Column(name="name",unique = true)
    private String name;

    @Column(name = "password")
    private String password;

    @Column(name="company_name")
    private String companyName;

    @Column(name = "company_code")
    private String companyCode;

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public String getCompanyCode() {
        return companyCode;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyCode(String companyCode) {
        this.companyCode = companyCode;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public void setName(String name) {
        this.name = name;
    }
}
