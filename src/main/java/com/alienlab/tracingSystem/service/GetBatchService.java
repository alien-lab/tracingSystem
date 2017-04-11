package com.alienlab.tracingSystem.service;

import com.alienlab.tracingSystem.Repository.BatchInfoRepository;
import com.alienlab.tracingSystem.Repository.BatchItemRepository;
import com.alienlab.tracingSystem.Repository.FarmItemRepository;
import com.alienlab.tracingSystem.Repository.ProductItemRepository;
import com.alienlab.tracingSystem.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by master on 2017/4/11.
 */
@Service
public class GetBatchService {
    @Autowired
    private BatchInfoRepository batchInfoRepository;
    @Autowired
    private BatchItemRepository batchItemRepository;
    @Autowired
    private ProductItemRepository productItemRepository;
    @Autowired
    private FarmItemRepository farmItemRepository;

    public BatchInfo findBatchInfoByBatchString(String batchString){
        return batchInfoRepository.findBatchInfoByBatchString(batchString);
    }
    //查批次详情
    public List<DescItem> findDescItemByBatchString(String batchString){
        List<BatchItem> batchItems=batchItemRepository.findBatchItemByBatchInfoIdOrderByIdDesc(batchInfoRepository.findBatchInfoByBatchString(batchString));
        List<DescItem> descItems=new ArrayList<>();
        for (BatchItem batchItem:batchItems){
            String status=batchItem.getDetailContentId().getItemStatus();
            if (status!=null&&status.equals("1")){
               descItems.add(batchItem.getDetailContentId());
            }
        }
        return descItems ;
    }
    //查批次对应产品详情
    public List<DescItem> findProductDescByBatchString(String batchString){
        ProductInfo product = batchInfoRepository.findBatchInfoByBatchString(batchString).getProductByProductId();
        List<ProductItem> productItems = productItemRepository.findProductItemsByProductInfoByProductInfoIdOrderByIdDesc(product);
        List<DescItem> descItems=new ArrayList<>();
        for (ProductItem productItem:productItems){
            String status=productItem.getDescItemByDetailcontentId().getItemStatus();
            if (status!=null&&status.equals("1")){
               descItems.add(productItem.getDescItemByDetailcontentId());
            }
        }
        return descItems;
    }
    //查批次对应农场详情
    public List<DescItem> findFarmDescByBatchString(String batchString){
        List<FarmItem> farmItems=farmItemRepository.findFarmItemsByFarmInfoByFarmInfoIdOrderByIdDesc(batchInfoRepository.findBatchInfoByBatchString(batchString).getProductByProductId().getFarmNameByFarmId());
        List<DescItem> descItems=new ArrayList<>();
        for (FarmItem farmItem:farmItems){
            String status=farmItem.getDescItemByDetailcontentId().getItemStatus();
            if (status!=null&&status.equals("1")){
                descItems.add(farmItem.getDescItemByDetailcontentId());
            }
        }
        return descItems;
    }
}
