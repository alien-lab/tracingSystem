package com.alienlab.tracingSystem.controller;

import com.alienlab.tracingSystem.Repository.FarmInfoRepository;
import com.alienlab.tracingSystem.entity.FarmInfo;
import com.alienlab.tracingSystem.service.FarmInfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

/**
 * Created by master on 2017/3/26.
 */
@Api(value="/api/farmInfos",description="农场信息Api")
public class FarmInfoController {
    @Autowired
    private FarmInfoService farmInfoService;
    @Autowired
    private FarmInfoRepository farmInfoRepository;
    @ApiOperation(value="获取所有农场信息")
    public ResponseEntity listFarmInfos(){
        return null;
    }
}
