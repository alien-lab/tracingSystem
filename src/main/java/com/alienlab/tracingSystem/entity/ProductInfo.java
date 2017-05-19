package com.alienlab.tracingSystem.entity;

import javax.persistence.*;
import java.util.Arrays;

/**
 * Created by master on 2017/4/6.
 */
@Entity
@Table(name = "product_info", schema = "trancingsystem", catalog = "")
public class ProductInfo {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;
    private String productName;
    private String productLogo;
    private double productPrice;
    private String productFlag;
    private String productTitle;
    private String productContent;
    private String productDesc;
    private byte[] productImage;
    private String productImageContentType;
    private String productStatus;
    private long nice;
    private long bad;
    private long collection;
    private long looks;
    @ManyToOne
    @JoinColumn(name = "farm_info_id", referencedColumnName = "id")
    private FarmInfo farmNameByFarmId;
   /* @ManyToOne
    @JoinColumn(name = "product_type_id", referencedColumnName = "id")
    private ProductType typeNameByProductId;*/

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
    @Basic
    @Column(name = "product_name")
    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }
    @Basic
    @Column(name = "product_logo")
    public String getProductLogo() {
        return productLogo;
    }

    public void setProductLogo(String productLogo) {
        this.productLogo = productLogo;
    }

    @Basic
    @Column(name = "product_price")
    public double getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(double productPrice) {
        this.productPrice = productPrice;
    }
    @Basic
    @Column(name = "product_flag")
    public String getProductFlag() {
        return productFlag;
    }

    public void setProductFlag(String productFlag) {
        this.productFlag = productFlag;
    }
    @Basic
    @Column(name = "product_title")
    public String getProductTitle() {
        return productTitle;
    }

    public void setProductTitle(String productTitle) {
        this.productTitle = productTitle;
    }

    @Basic
    @Column(name = "product_desc")
    public String getProductDesc() {
        return productDesc;
    }

    public void setProductDesc(String productDesc) {
        this.productDesc = productDesc;
    }
    @Basic
    @Column(name = "product_image")
    public byte[] getProductImage() {
        return productImage;
    }

    public void setProductImage(byte[] productImage) {
        this.productImage = productImage;
    }
    @Basic
    @Column(name = "product_image_content_type")
    public String getProductImageContentType() {
        return productImageContentType;
    }

    public void setProductImageContentType(String productImageContentType) {
        this.productImageContentType = productImageContentType;
    }
    @Basic
    @Column(name = "product_status")
    public String getProductStatus() {
        return productStatus;
    }

    public void setProductStatus(String productStatus) {
        this.productStatus = productStatus;
    }

    public FarmInfo getFarmNameByFarmId() {
        return farmNameByFarmId;
    }

    public void setFarmNameByFarmId(FarmInfo farmNameByFarmId) {
        this.farmNameByFarmId = farmNameByFarmId;
    }
    @Basic
    @Column(name = "product_content")
    public String getProductContent() {
        return productContent;
    }

    public void setProductContent(String productContent) {
        this.productContent = productContent;
    }
    @Basic
    @Column(name = "product_nice")
    public long getNice() {
        return nice;
    }

    public void setNice(long nice) {
        this.nice = nice;
    }
    @Basic
    @Column(name = "product_bad")
    public long getBad() {
        return bad;
    }

    public void setBad(long bad) {
        this.bad = bad;
    }
    @Basic
    @Column(name = "collection")
    public long getCollection() {
        return collection;
    }

    public void setCollection(long collection) {
        this.collection = collection;
    }
    @Basic
    @Column(name = "looks")
    public long getLooks() {
        return looks;
    }

    public void setLooks(long looks) {
        this.looks = looks;
    }
    /* public ProductType getTypeNameByProductId() {
        return typeNameByProductId;
    }

    public void setTypeNameByProductId(ProductType typeNameByProductId) {
        this.typeNameByProductId = typeNameByProductId;
    }*/

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

       ProductInfo that = (ProductInfo) o;

        if (id != that.id) return false;
        if (Double.compare(that.productPrice, productPrice) != 0) return false;
        if (productName != null ? !productName.equals(that.productName) : that.productName != null) return false;
        if (productFlag != null ? !productFlag.equals(that.productFlag) : that.productFlag != null) return false;
        if (productDesc != null ? !productDesc.equals(that.productDesc) : that.productDesc != null) return false;
        if (!Arrays.equals(productImage, that.productImage)) return false;
        if (productImageContentType != null ? !productImageContentType.equals(that.productImageContentType) : that.productImageContentType != null)
            return false;
        if (productStatus != null ? !productStatus.equals(that.productStatus) : that.productStatus != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = (int) (id ^ (id >>> 32));
        result = 31 * result + (productName != null ? productName.hashCode() : 0);
        temp = Double.doubleToLongBits(productPrice);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (productFlag != null ? productFlag.hashCode() : 0);
        result = 31 * result + (productDesc != null ? productDesc.hashCode() : 0);
        result = 31 * result + Arrays.hashCode(productImage);
        result = 31 * result + (productImageContentType != null ? productImageContentType.hashCode() : 0);
        result = 31 * result + (productStatus != null ? productStatus.hashCode() : 0);
        return result;
    }
}

