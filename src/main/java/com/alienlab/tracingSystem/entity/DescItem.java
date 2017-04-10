package com.alienlab.tracingSystem.entity;

import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import java.sql.Timestamp;


/**
 * Created by master on 2017/3/26.
 */
@Entity
@Table(name = "desc_item", schema = "trancingsystem", catalog = "")
public class DescItem {
    @ApiModelProperty(value="详情编码")
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;
    @ApiModelProperty(value="详情类型：文字或图片")
    private String itemType;
    @ApiModelProperty(value="详情标签")
    private String itemLabel;
    @ApiModelProperty(value="详情内容")
    private String itemContent;
    @ApiModelProperty(value="详情文件流")
    private String itemFile;
    @ApiModelProperty(value="详情文件流类型（后缀名）")
    private String itemFileContentType;
    @ApiModelProperty(value="详情链接")
    private String itemLink;
    @ApiModelProperty(value="详情创建时间")
    private Timestamp itemTime;
    @ApiModelProperty(value="详情排序")
    private Integer itemOrder;
    @ApiModelProperty(value="详情状态（0已发布，1未发布）")
    private String itemStatus;

    public DescItem() {
    }

    public DescItem( String itemType, String itemLabel, String itemContent, String itemFile, String itemFileContentType, String itemLink, Timestamp itemTime, Integer itemOrder/*,String itemStatus*/) {
        this.itemType = itemType;
        this.itemLabel = itemLabel;
        this.itemContent = itemContent;
        this.itemFile = itemFile;
        this.itemFileContentType = itemFileContentType;
        this.itemLink = itemLink;
        this.itemTime = itemTime;
        this.itemOrder = itemOrder;
       // this.itemStatus=itemStatus;
    }

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

    @Basic
    @Column(name = "item_type")
    public String getItemType() {
        return itemType;
    }

    public void setItemType(String itemType) {
        this.itemType = itemType;
    }

    @Basic
    @Column(name = "item_label")
    public String getItemLabel() {
        return itemLabel;
    }

    public void setItemLabel(String itemLabel) {
        this.itemLabel = itemLabel;
    }

    @Basic
    @Column(name = "item_content")
    public String getItemContent() {
        return itemContent;
    }

    public void setItemContent(String itemContent) {
        this.itemContent = itemContent;
    }

    @Basic
    @Column(name = "item_file")
    public String getItemFile() {
        return itemFile;
    }

    public void setItemFile(String itemFile) {
        this.itemFile = itemFile;
    }

    @Basic
    @Column(name = "item_file_content_type")
    public String getItemFileContentType() {
        return itemFileContentType;
    }

    public void setItemFileContentType(String itemFileContentType) {
        this.itemFileContentType = itemFileContentType;
    }

    @Basic
    @Column(name = "item_link")
    public String getItemLink() {
        return itemLink;
    }

    public void setItemLink(String itemLink) {
        this.itemLink = itemLink;
    }

    @Basic
    @Column(name = "item_time")
    public Timestamp getItemTime() {
        return itemTime;
    }

    public void setItemTime(Timestamp itemTime) {
        this.itemTime = itemTime;
    }

    @Basic
    @Column(name = "item_order")
    public Integer getItemOrder() {
        return itemOrder;
    }
    public void setItemOrder(Integer itemOrder) {
        this.itemOrder = itemOrder;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

       DescItem that = (DescItem) o;

        if (id != that.id) return false;
        if (itemType != null ? !itemType.equals(that.itemType) : that.itemType != null) return false;
        if (itemLabel != null ? !itemLabel.equals(that.itemLabel) : that.itemLabel != null) return false;
        if (itemContent != null ? !itemContent.equals(that.itemContent) : that.itemContent != null) return false;
        if (itemFile != null ? !itemFile.equals(that.itemFile) : that.itemFile != null) return false;
        if (itemFileContentType != null ? !itemFileContentType.equals(that.itemFileContentType) : that.itemFileContentType != null)
            return false;
        if (itemLink != null ? !itemLink.equals(that.itemLink) : that.itemLink != null) return false;
        if (itemTime != null ? !itemTime.equals(that.itemTime) : that.itemTime != null) return false;
        if (itemOrder != null ? !itemOrder.equals(that.itemOrder) : that.itemOrder != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (itemType != null ? itemType.hashCode() : 0);
        result = 31 * result + (itemLabel != null ? itemLabel.hashCode() : 0);
        result = 31 * result + (itemContent != null ? itemContent.hashCode() : 0);
        result = 31 * result + (itemFile != null ? itemFile.hashCode() : 0);
        result = 31 * result + (itemFileContentType != null ? itemFileContentType.hashCode() : 0);
        result = 31 * result + (itemLink != null ? itemLink.hashCode() : 0);
        result = 31 * result + (itemTime != null ? itemTime.hashCode() : 0);
        result = 31 * result + (itemOrder != null ? itemOrder.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "DescItem{" +
                "id=" + id +
                ", itemType='" + itemType + '\'' +
                ", itemLabel='" + itemLabel + '\'' +
                ", itemContent='" + itemContent + '\'' +
                ", itemFile=" + (itemFile) +     '\''    +
                ", itemFileContentType='" + itemFileContentType + '\'' +
                ", itemLink='" + itemLink + '\'' +
                ", itemTime=" + itemTime +
                ", itemOrder=" + itemOrder +
                '}';
    }
}
