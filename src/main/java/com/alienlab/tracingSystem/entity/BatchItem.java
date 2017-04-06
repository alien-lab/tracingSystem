package com.alienlab.tracingSystem.entity;

import javax.persistence.*;

/**
 * Created by master on 2017/4/6.
 */
@Entity
@Table(name = "batch_item", schema = "trancingsystem", catalog = "")
public class BatchItem {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;
    private String itemStatus;
    @ManyToOne
    @JoinColumn(name = "batch_info_id", referencedColumnName = "id")
    private BatchInfo batchInfoId;
    @OneToOne
    @JoinColumn(name = "detailcontent_id", referencedColumnName = "id")
    private DescItem detailContentId;

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

        BatchItem that = (BatchItem) o;

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
    public BatchInfo getBatchInfoId() {
        return batchInfoId;
    }

    public void setBatchInfoId(BatchInfo batchInfoId) {
        this.batchInfoId = batchInfoId;
    }

    public DescItem getDetailContentId() {
        return detailContentId;
    }

    public void setDetailContentId(DescItem detailContentId) {
        this.detailContentId = detailContentId;
    }
}
