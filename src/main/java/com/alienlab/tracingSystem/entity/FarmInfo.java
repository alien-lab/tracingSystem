package com.alienlab.tracingSystem.entity;

import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;

/**
 * Created by master on 2017/3/26.
 */
@Entity
@Table(name = "farm_info",schema = "trancingsystem",catalog = "")
public class FarmInfo {
    @ApiModelProperty(value="农场d")
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;
    @ApiModelProperty(value="农场名称")
    private String farmName;
    private String farmImage;
    private String farmTitle;
    private String farmContent;
    private String farmDesc;
    private Long likes;
    private Long dislikes;
    private Long cares;
    private Long looks;
    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "farm_name")
    public String getFarmName() {
        return farmName;
    }

    public void setFarmName(String farmName) {
        this.farmName = farmName;
    }

    @Basic
    @Column(name = "farm_image")
    public String getFarmImage() {
        return farmImage;
    }

    public void setFarmImage(String farmImage) {
        this.farmImage = farmImage;
    }
    @Basic
    @Column(name = "farm_title")
    public String getFarmTitle() {
        return farmTitle;
    }

    public void setFarmTitle(String farmTitle) {
        this.farmTitle = farmTitle;
    }
    @Basic
    @Column(name = "farm_content")
    public String getFarmContent() {
        return farmContent;
    }

    public void setFarmContent(String farmContent) {
        this.farmContent = farmContent;
    }
    @Basic
    @Column(name = "farm_desc")
    public String getFarmDesc() {
        return farmDesc;
    }

    public void setFarmDesc(String farmDesc) {
        this.farmDesc = farmDesc;
    }
    @Basic
    @Column(name = "likes")
    public Long getLikes() {
        return likes;
    }

    public void setLikes(Long likes) {
        this.likes = likes;
    }
    @Basic
    @Column(name = "dislikes")
    public Long getDislikes() {
        return dislikes;
    }

    public void setDislikes(Long dislikes) {
        this.dislikes = dislikes;
    }
    @Basic
    @Column(name = "cares")
    public Long getCares() {
        return cares;
    }

    public void setCares(Long cares) {
        this.cares = cares;
    }
    @Basic
    @Column(name = "looks")
    public Long getLooks() {
        return looks;
    }

    public void setLooks(Long looks) {
        this.looks = looks;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        FarmInfo that = (FarmInfo) o;

        if (id != that.id) return false;
        if (farmName != null ? !farmName.equals(that.farmName) : that.farmName != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (farmName != null ? farmName.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "FarmInfo{" +
                "id=" + id +
                ", farmName='" + farmName + '\'' +
                '}';
    }
}
