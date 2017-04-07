package com.alienlab.tracingSystem.service;

import com.alienlab.tracingSystem.Repository.BatchInfoRepository;
import com.alienlab.tracingSystem.Repository.BatchItemRepository;
import com.alienlab.tracingSystem.Repository.DescItemRepository;
import com.alienlab.tracingSystem.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by master on 2017/4/6.
 */
@Service
public class BatchItemService {
    @Autowired
    private BatchItemRepository batchItemRepository;
    @Autowired
    private BatchInfoRepository batchInfoRepository;

    public BatchItem  addDesc(BatchItem batchItem){
        return batchItemRepository.save(batchItem);
    }
    /*public List<BatchItem> getItems(Long batchid){
        BatchInfo batchInfo=batchInfoRepository.findOne(batchid);
        if(batchInfo!=null){
         //   List<BatchItem> result= batchItemRepository.findBatchItemsByBatchInfoByBatchInfoIdOrderByIdDesc(batchInfo);
            return null;
        }else{
            return null;
        }
    }*/
    public List<BatchItem> getItems(long batchid){
        BatchInfo batch = batchInfoRepository.findOne(batchid);
        return batchItemRepository.findBatchItemByBatchInfoIdOrderByIdDesc(batch);
    }
}
