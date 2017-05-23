package com.alienlab.tracingSystem.entity;

import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;

/**
 * Created by master on 2017/5/21.
 */
@Entity
@Table(name = "tb_collect",schema = "trancingsystem",catalog = "")
public class Collect {
    @ApiModelProperty(value="id")
    private long id;
    @ApiModelProperty(value="收藏产品的id")
    private long productId;
    @ApiModelProperty(value="收藏人账号")
    private String account;

    public Collect() {
    }

    public Collect(long id, long productId, String account) {
        this.id = id;
        this.productId = productId;
        this.account = account;
    }

    public Collect(long productId, String account) {
        this.productId = productId;
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

    public long getProductId() {
        return productId;
    }

    public void setProductId(long productId) {
        this.productId = productId;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }
}
