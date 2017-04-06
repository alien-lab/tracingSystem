package com.alienlab.tracingSystem.controller;

import com.alienlab.tracingSystem.service.BatchItemService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by master on 2017/4/6.
 */
@Api(value="/api/batchItem",description="批次详情Api")
@RestController
public class BatchItemController {
    @Autowired
    private BatchItemService batchItemService;
}
