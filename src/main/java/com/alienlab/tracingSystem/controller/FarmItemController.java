package com.alienlab.tracingSystem.controller;

import com.alienlab.tracingSystem.Repository.FarmItemRepository;
import com.alienlab.tracingSystem.service.FarmItemService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by master on 2017/3/26.
 */
@Api(value="/api/farmItem",description="农场详情Api")
public class FarmItemController {
    @Autowired
    private FarmItemService farmItemService;
    @Autowired
    private FarmItemRepository farmItemRepository;

}
