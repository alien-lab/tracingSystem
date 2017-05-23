package com.alienlab.tracingSystem.service;

import com.alienlab.tracingSystem.Repository.CollectRepostory;
import com.alienlab.tracingSystem.Repository.ProductInfoRepository;
import com.alienlab.tracingSystem.entity.Care;
import com.alienlab.tracingSystem.entity.Collect;
import com.alienlab.tracingSystem.entity.ProductInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by master on 2017/5/21.
 */
@Service
public class CollectService {
    @Autowired
    private CollectRepostory collectRepostory;
    @Autowired
    private ProductInfoRepository productInfoRepository;
    public List<Collect> findCollectsByAccount(String account) {
        return collectRepostory.findByAccount( account);
    }
    public void addCollects(Collect collect){
         collectRepostory.save(collect);
        ProductInfo productInfo=productInfoRepository.findOne(collect.getProductId());
        productInfo.setCollection(productInfo.getCollection()+1);
        productInfoRepository.save(productInfo);
    }
    public Boolean checkIfCollect(long id, String account){
        Collect collect = null;
        collect =  collectRepostory.findByProductIdAndAccount(id,account);
        if(collect != null){
            return true;
        }
        return  false;
    }
}
