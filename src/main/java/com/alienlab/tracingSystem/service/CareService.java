package com.alienlab.tracingSystem.service;

import com.alienlab.tracingSystem.Repository.CareRepository;
import com.alienlab.tracingSystem.entity.Care;
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
    public List<Care> findCaresByAccount(String account) {
        return careRepository.findByAccount( account);
    }
    public Care addCare(Care care){
        return careRepository.save(care);
    }
}
