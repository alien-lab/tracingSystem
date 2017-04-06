package com.alienlab.tracingSystem.service;

import com.alienlab.tracingSystem.Repository.ProductInfoRepository;
import com.alienlab.tracingSystem.Repository.ProductItemRepository;
import com.alienlab.tracingSystem.entity.FarmInfo;
import com.alienlab.tracingSystem.entity.FarmItem;
import com.alienlab.tracingSystem.entity.ProductInfo;
import com.alienlab.tracingSystem.entity.ProductItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by master on 2017/4/6.
 */
@Service
public class ProductItemService {
    @Autowired
    private ProductItemRepository productItemRepository;
    @Autowired
    private ProductInfoRepository productInfoRepository;
    //1.添加农场详情信息
    public ProductItem addDesc(ProductItem productItem){
        return productItemRepository.save(productItem);
    }
    //2.显示某农场的所有详情
    public List<ProductItem> getItems(Long pid){
        ProductInfo productInfo=productInfoRepository.findOne(pid);
        if(productInfo!=null){
            List<ProductItem> result= productItemRepository.findProductItemsByProductInfoByProductInfoIdOrderByIdDesc(productInfo);
            return result;
        }else{
            return null;
        }
    }
}
