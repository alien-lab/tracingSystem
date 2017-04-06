package com.alienlab.tracingSystem.service;

import com.alienlab.tracingSystem.Repository.FarmInfoRepository;
import com.alienlab.tracingSystem.Repository.FarmItemRepository;
import com.alienlab.tracingSystem.entity.FarmInfo;
import com.alienlab.tracingSystem.entity.FarmItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by master on 2017/3/26.
 */
@Service
public class FarmItemService {
    @Autowired
    private FarmInfoRepository farmInfoRepository;
    @Autowired
    private FarmItemRepository farmItemRepository;
    //1.添加农场详情信息
    public FarmItem addItem(FarmItem farmItem){
        return farmItemRepository.save(farmItem);
    }
    //2.显示某农场的所有详情
    public List<FarmItem> getItems(Long farmid){
       FarmInfo farmInfo=farmInfoRepository.findOne(farmid);
        if(farmInfo!=null){
            List<FarmItem> result= farmItemRepository.findFarmItemsByFarmInfoByFarmInfoIdOrderByIdDesc(farmInfo);
            return result;
        }else{
            return null;
        }
    }
}
