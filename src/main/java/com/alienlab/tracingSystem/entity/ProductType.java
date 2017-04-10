package com.alienlab.tracingSystem.entity;

import javax.persistence.*;

/**
 * Created by master on 2017/4/8.
 */
@Entity
@Table(name = "product_type", schema = "trancingsystem", catalog = "")
public class ProductType {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;
    private String productTypeName;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
    @Basic
    @Column(name = "product_type_name")
    public String getProductTypeName() {
        return productTypeName;
    }

    public void setProductTypeName(String productTypeName) {
        this.productTypeName = productTypeName;
    }
}
