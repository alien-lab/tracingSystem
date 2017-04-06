package com.alienlab.tracingSystem.entity;

import javax.persistence.*;

/**
 * Created by master on 2017/4/6.
 */
@Entity
@Table(name = "product_item", schema = "trancingsystem", catalog = "")
public class ProductItem {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;
    private String itemStatus;
    @ManyToOne
    @JoinColumn(name = "product_info_id", referencedColumnName = "id")
    private ProductInfo productInfoByProductInfoId;
    @OneToOne
    @JoinColumn(name = "detailcontent_id", referencedColumnName = "id")
    private DescItem descItemByDetailcontentId;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
    @Basic
    @Column(name = "item_status")
    public String getItemStatus() {
        return itemStatus;
    }

    public void setItemStatus(String itemStatus) {
        this.itemStatus = itemStatus;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ProductItem that = (ProductItem) o;

        if (id != that.id) return false;
        if (itemStatus != null ? !itemStatus.equals(that.itemStatus) : that.itemStatus != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (itemStatus != null ? itemStatus.hashCode() : 0);
        return result;
    }

    public ProductInfo getProductInfoByProductInfoId() {
        return productInfoByProductInfoId;
    }

    public void setProductInfoByProductInfoId(ProductInfo productInfoByProductInfoId) {
        this.productInfoByProductInfoId = productInfoByProductInfoId;
    }

    public DescItem getDescItemByDetailcontentId() {
        return descItemByDetailcontentId;
    }

    public void setDescItemByDetailcontentId(DescItem descItemByDetailcontentId) {
        this.descItemByDetailcontentId = descItemByDetailcontentId;
    }
}
