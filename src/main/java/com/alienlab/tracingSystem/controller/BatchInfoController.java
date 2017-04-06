package com.alienlab.tracingSystem.controller;

import com.alienlab.tracingSystem.service.BatchInfoService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by master on 2017/4/6.
 */
@Api(value="/api/batchInfo",description="批次Api")
@RestController
public class BatchInfoController {
    @Autowired
    private BatchInfoService batchInfoService;
}
