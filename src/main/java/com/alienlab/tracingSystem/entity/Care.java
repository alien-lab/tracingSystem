package com.alienlab.tracingSystem.entity;

import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by master on 2017/5/15.
 */
@Entity
@Table(name = "tb_care",schema = "trancingsystem",catalog = "")
public class Care implements Serializable {
    @ApiModelProperty(value="id")
    private long id;
    @ApiModelProperty(value="关注农场的id")
    private long farmId;
    @ApiModelProperty(value="关注人账号")
    private String account;

    public Care(long id, long farmId, String account) {
        this.id = id;
        this.farmId = farmId;
        this.account = account;
    }

    public Care() {
    }

    public Care(long farmId, String account) {
        this.farmId = farmId;
        this.account = account;
    }


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
    @Column(name = "farm_id")
    public long getFarmId() {
        return farmId;
    }

    public void setFarmId(long farmId) {
        this.farmId = farmId;
    }
    @Basic
    @Column(name = "account")
    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }
}
