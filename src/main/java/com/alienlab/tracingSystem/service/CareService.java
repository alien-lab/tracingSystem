package com.alienlab.tracingSystem.service;

import com.alienlab.tracingSystem.Repository.CareRepository;
import com.alienlab.tracingSystem.Repository.FarmInfoRepository;
import com.alienlab.tracingSystem.entity.Care;
import com.alienlab.tracingSystem.entity.FarmInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by master on 2017/5/15.
 */
@Service
public class CareService {
    @Autowired
    private CareRepository careRepository;
    @Autowired
    private FarmInfoRepository farmInfoRepository;
    public List<Care> findCaresByAccount(String account) {
        return careRepository.findByAccount( account);
    }
    public void  addCares(Care care){
        careRepository.save(care);
        FarmInfo farmInfo=farmInfoRepository.findOne(care.getFarmId());
        farmInfo.setCares(farmInfo.getCares() +1);
        farmInfoRepository.save(farmInfo);
    }
    public Boolean checkIfCare(long id, String account){
        Care care = null;
        care =  careRepository.findByFarmIdAndAccount(id,account);
        if(care != null){
            return true;
        }
        return  false;
    }
}
