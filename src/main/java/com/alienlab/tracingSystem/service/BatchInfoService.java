package com.alienlab.tracingSystem.service;

import com.alienlab.tracingSystem.Repository.BatchInfoRepository;
import com.alienlab.tracingSystem.entity.BatchInfo;
import com.alienlab.tracingSystem.entity.FarmInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

/**
 * Created by master on 2017/4/6.
 */
@Service
public class BatchInfoService {
    @Autowired
    private BatchInfoRepository batchInfoRepository;
    public Page<BatchInfo> getBatchPages(int index, int size){
        return  batchInfoRepository.findAll(new PageRequest(index, size));
    }
    public BatchInfo getBatchInfoById(long id){
        return batchInfoRepository.findOne(id);
    }
    public BatchInfo addBatchInfo(BatchInfo batch){
        if(batch != null){
            return batchInfoRepository.save(batch);
        }else{
            return null;
        }
    }
    public boolean deleteBatchInfoById(Long id){
        try{
            batchInfoRepository.delete(id);
            return true;
        }catch(Exception e){
            e.printStackTrace();
            return false;
        }
    }

    public BatchInfo updateBatchInfo(BatchInfo batch){
        return batchInfoRepository.save(batch);
    }

}
