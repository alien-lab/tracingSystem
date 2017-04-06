package com.alienlab.tracingSystem.entity;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * Created by master on 2017/4/6.
 */
@Entity
@Table(name = "batch_info", schema = "trancingsystem", catalog = "")
public class BatchInfo {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;
    private String batchString;
    private String batchName;
    private int batchNoStart;
    private int batchNoEnd;
    private Timestamp batchTime;
    private Long batchTotalCount;
    private String batchStatus;
    private Timestamp batchLastTime;
    @ManyToOne
    @JoinColumn(name = "product_id", referencedColumnName = "id")
     private ProductInfo  productByProductId;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
    @Basic
    @Column(name = "batch_string")
    public String getBatchString() {
        return batchString;
    }

    public void setBatchString(String batchString) {
        this.batchString = batchString;
    }
    @Basic
    @Column(name = "batch_name")
    public String getBatchName() {
        return batchName;
    }

    public void setBatchName(String batchName) {
        this.batchName = batchName;
    }
    @Basic
    @Column(name = "batch_no_start")
    public int getBatchNoStart() {
        return batchNoStart;
    }

    public void setBatchNoStart(int batchNoStart) {
        this.batchNoStart = batchNoStart;
    }
    @Basic
    @Column(name = "batch_no_end")
    public int getBatchNoEnd() {
        return batchNoEnd;
    }

    public void setBatchNoEnd(int batchNoEnd) {
        this.batchNoEnd = batchNoEnd;
    }
    @Basic
    @Column(name = "batch_time")
    public Timestamp getBatchTime() {
        return batchTime;
    }

    public void setBatchTime(Timestamp batchTime) {
        this.batchTime = batchTime;
    }
    @Basic
    @Column(name = "batch_total_count")
    public Long getBatchTotalCount() {
        return batchTotalCount;
    }

    public void setBatchTotalCount(Long batchTotalCount) {
        this.batchTotalCount = batchTotalCount;
    }
    @Basic
    @Column(name = "batch_status")
    public String getBatchStatus() {
        return batchStatus;
    }

    public void setBatchStatus(String batchStatus) {
        this.batchStatus = batchStatus;
    }
    @Basic
    @Column(name = "batch_last_time")
    public Timestamp getBatchLastTime() {
        return batchLastTime;
    }

    public void setBatchLastTime(Timestamp batchLastTime) {
        this.batchLastTime = batchLastTime;
    }

    public ProductInfo getProductByProductId() {
        return productByProductId;
    }

    public void setProductByProductId(ProductInfo productByProductId) {
        this.productByProductId = productByProductId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BatchInfo)) return false;

        BatchInfo batchInfo = (BatchInfo) o;

        if (getId() != batchInfo.getId()) return false;
        if (getBatchNoStart() != batchInfo.getBatchNoStart()) return false;
        if (getBatchNoEnd() != batchInfo.getBatchNoEnd()) return false;
        if (getBatchString() != null ? !getBatchString().equals(batchInfo.getBatchString()) : batchInfo.getBatchString() != null)
            return false;
        if (getBatchName() != null ? !getBatchName().equals(batchInfo.getBatchName()) : batchInfo.getBatchName() != null)
            return false;
        if (getBatchTime() != null ? !getBatchTime().equals(batchInfo.getBatchTime()) : batchInfo.getBatchTime() != null)
            return false;
        if (getBatchTotalCount() != null ? !getBatchTotalCount().equals(batchInfo.getBatchTotalCount()) : batchInfo.getBatchTotalCount() != null)
            return false;
        if (getBatchStatus() != null ? !getBatchStatus().equals(batchInfo.getBatchStatus()) : batchInfo.getBatchStatus() != null)
            return false;
        return getBatchLastTime() != null ? getBatchLastTime().equals(batchInfo.getBatchLastTime()) : batchInfo.getBatchLastTime() == null;

    }

    @Override
    public int hashCode() {
        int result = (int) (getId() ^ (getId() >>> 32));
        result = 31 * result + (getBatchString() != null ? getBatchString().hashCode() : 0);
        result = 31 * result + (getBatchName() != null ? getBatchName().hashCode() : 0);
        result = 31 * result + getBatchNoStart();
        result = 31 * result + getBatchNoEnd();
        result = 31 * result + (getBatchTime() != null ? getBatchTime().hashCode() : 0);
        result = 31 * result + (getBatchTotalCount() != null ? getBatchTotalCount().hashCode() : 0);
        result = 31 * result + (getBatchStatus() != null ? getBatchStatus().hashCode() : 0);
        result = 31 * result + (getBatchLastTime() != null ? getBatchLastTime().hashCode() : 0);
        return result;
    }
}
