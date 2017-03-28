package com.alienlab.tracingSystem.service;

import com.alienlab.tracingSystem.Repository.FarmInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by master on 2017/3/26.
 */
@Service
public class FarmInfoService {
    @Autowired
    private FarmInfoRepository farmInfoRepository;
}
