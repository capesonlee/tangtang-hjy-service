package com.lijuyong.startup.entity;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Created by john on 2017/4/17.
 */
@Entity(name = "t_wechat")
public class WeChatDO {


    @Column(name = "id",nullable = false)
    private  Integer id;


    @Id
    @Column(name = "open_id")
    private String openId;

    @Column(name = "head_img_url")
    private String headImgUrl;

    @Column(name = "nick_name")
    private String nickName;

    @Column(name = "app_id")
    private String appId;

    public Integer getId() {
        return id;
    }

    public String getAppId() {
        return appId;
    }

    public String getHeadImgUrl() {
        return headImgUrl;
    }

    public String getNickName() {
        return nickName;
    }

    public String getOpenId() {
        return openId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public void setHeadImgUrl(String headImgUrl) {
        this.headImgUrl = headImgUrl;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

}
