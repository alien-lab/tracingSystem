package com.alienlab.tracingSystem.entity;

import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;

/**
 * Created by master on 2017/3/26.
 */
@Entity
@Table(name = "farm_item", schema = "trancingsystem", catalog = "")
public class FarmItem {
    @ApiModelProperty(value="农场详情id")
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;
    @ApiModelProperty(value="农场详情状态（发布、未发布）")
    private String itemStatus;
    @ApiModelProperty(value="外键，农场编码")
    @ManyToOne
    @JoinColumn(name = "farm_info_id", referencedColumnName = "id")
    private FarmInfo farmInfoByFarmInfoId;
    @ApiModelProperty(value="农场详情编码")
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

       FarmItem that = (FarmItem) o;

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

    public FarmInfo getFarmInfoByFarmInfoId() {
        return farmInfoByFarmInfoId;
    }

    public void setFarmInfoByFarmInfoId(FarmInfo farmInfoByFarmInfoId) {
        this.farmInfoByFarmInfoId = farmInfoByFarmInfoId;
    }

    public DescItem getDescItemByDetailcontentId() {
        return descItemByDetailcontentId;
    }

    public void setDescItemByDetailcontentId(DescItem descItemByDetailcontentId) {
        this.descItemByDetailcontentId = descItemByDetailcontentId;
    }
}
