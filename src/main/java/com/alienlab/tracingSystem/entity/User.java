package com.alienlab.tracingSystem.entity;

import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by master on 2017/3/26.
 */
@Entity
@Table(name = "tb_admin",schema = "trancingsystem",catalog = "")
public class User {
    @ApiModelProperty(value="用户id")
    private long userid;
    @ApiModelProperty(value="用户名")
    private String username;
    @ApiModelProperty(value="用户登录时间")
    private String loginname;
    @ApiModelProperty(value="用户密码")
    private String password;
    @ApiModelProperty(value="用户最后登录时间")
    private Date lastlogin;
    @ApiModelProperty(value="用户状态")
    private String userstatus;


    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "user_id")
    public long getUserid() {
        return userid;
    }

    public void setUserid(long userid) {
        this.userid = userid;
    }
    @Basic
    @Column(name = "user_name")
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Basic
    @Column(name = "user_loginname")
    public String getLoginname() {
        return loginname;
    }

    public void setLoginname(String loginname) {
        this.loginname = loginname;
    }

    @Basic
    @Column(name = "user_pwd")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Basic
    @Column(name = "user_lastlogin")
    public Date getLastlogin() {
        return lastlogin;
    }

    public void setLastlogin(Date lastlogin) {
        this.lastlogin = lastlogin;
    }

    @Basic
    @Column(name = "user_status")
    public String getUserstatus() {
        return userstatus;
    }

    public void setUserstatus(String userstatus) {
        this.userstatus = userstatus;
    }
}
