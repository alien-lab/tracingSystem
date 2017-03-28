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
        return "PushiFarmInfo{" +
                "id=" + id +
                ", farmName='" + farmName + '\'' +
                '}';
    }
}
