package com.alienlab.tracingSystem.entity;

import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;

/**
 * Created by master on 2017/5/13.
 */
@Entity
@Table(name = "tb_user",schema = "trancingsystem",catalog = "")
public class User {
    @ApiModelProperty(value="用户id")
    private long id;
    @ApiModelProperty(value="用户名")
    private String account;
    @ApiModelProperty(value="用户密码")
    private String password;
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
    @Column(name = "account")
    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }
    @Basic
    @Column(name = "password")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
