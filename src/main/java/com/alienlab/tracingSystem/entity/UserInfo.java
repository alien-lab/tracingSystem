package com.alienlab.tracingSystem.entity;

import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;

/**
 * Created by master on 2017/5/13.
 */
@Entity
@Table(name = "tb_userinfo",schema = "trancingsystem",catalog = "")
public class UserInfo {
    @ApiModelProperty(value="用户id")
    private long id;
    @ApiModelProperty(value="账号")
    private String account;
    @ApiModelProperty(value="昵称")
    private String nickname;
    @ApiModelProperty(value="性别")
    private String sex;
    @ApiModelProperty(value="头像")
    private String head;
    @ApiModelProperty(value="联系方式")
    private String phone;
    @ApiModelProperty(value="地址")
    private String address;
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
    @Column(name = "nickname")
    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }
    @Basic
    @Column(name = "sex")
    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }
    @Basic
    @Column(name = "head")
    public String getHead() {
        return head;
    }

    public void setHead(String head) {
        this.head = head;
    }
    @Basic
    @Column(name = "phone")
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
    @Basic
    @Column(name = "address")
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
