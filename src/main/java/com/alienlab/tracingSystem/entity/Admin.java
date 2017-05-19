package com.alienlab.tracingSystem.entity;

import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by master on 2017/5/13.
 */
@Entity
@Table(name = "tb_admin",schema = "trancingsystem",catalog = "")
public class Admin {
    @ApiModelProperty(value="用户id")
    private long id;
    @ApiModelProperty(value="用户名")
    private String name;
    @ApiModelProperty(value="用户登录时间")
    private String loginname;
    @ApiModelProperty(value="用户密码")
    private String password;
    @ApiModelProperty(value="用户最后登录时间")
    private Date lastlogin;
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "id")
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
    @Basic
    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    @Basic
    @Column(name = "login_name")
    public String getLoginname() {
        return loginname;
    }

    public void setLoginname(String loginname) {
        this.loginname = loginname;
    }
    @Basic
    @Column(name = "password")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    @Basic
    @Column(name = "last_login")
    public Date getLastlogin() {
        return lastlogin;
    }

    public void setLastlogin(Date lastlogin) {
        this.lastlogin = lastlogin;
    }

}
