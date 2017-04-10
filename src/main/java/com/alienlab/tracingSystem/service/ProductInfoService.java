package com.alienlab.tracingSystem.service;

import com.alienlab.tracingSystem.Repository.ProductInfoRepository;
import com.alienlab.tracingSystem.entity.FarmInfo;
import com.alienlab.tracingSystem.entity.ProductInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by master on 2017/4/6.
 */
@Service
public class ProductInfoService {
    @Autowired
    private ProductInfoRepository productInfoRepository;
    public List<ProductInfo> getProducts(){
        return productInfoRepository.findAll();
    }
    //1.展示所有产品的基本信息
    public Page<ProductInfo> getProductsPage(int index, int size){
        return productInfoRepository.findAll(new PageRequest(index,size));
    }
    //2.添加产品的基本信息
    public ProductInfo addProduct(ProductInfo product){
        product=productInfoRepository.save(product);
        return product;
    }
    //3.删除某个产品的基本信息
    public boolean deleteProduct(Long id){
        productInfoRepository.delete(id);
        return true;
    }
    //4.更新某个产品的基本信息
    public ProductInfo updateProduct(ProductInfo product){
        product=productInfoRepository.save(product);
        return product;
    }
    //5.通过farmid找农场
    public ProductInfo getProductInfoById(Long id){
        return productInfoRepository.findOne(id);
    }
}
