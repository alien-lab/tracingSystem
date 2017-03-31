package com.alienlab.tracingSystem.service;

import com.alienlab.tracingSystem.Repository.FarmInfoRepository;
import com.alienlab.tracingSystem.entity.FarmInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

/**
 * Created by master on 2017/3/26.
 */
@Service
public class FarmInfoService {
    @Autowired
    private FarmInfoRepository farmInfoRepository;
    public Page<FarmInfo> getFarmsPage(int index, int size){
        return farmInfoRepository.findAll(new PageRequest(index,size));
    }
    //1.添加农场
    public FarmInfo addFarm(FarmInfo farmInfo){
        if(farmInfo!=null){
            return farmInfoRepository.save(farmInfo);
        }else{
            return null;
        }
    }
    //2.删除某个农场基本信息
    public boolean deleteFarm(Long farmid) {
        try {
            farmInfoRepository.delete(farmid);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    //3.更新农场的基本信息
    public FarmInfo updateFarm(FarmInfo farmInfo){
        try {
            farmInfo = farmInfoRepository.save(farmInfo);
            return farmInfo;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
}
