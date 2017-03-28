package com.alienlab.tracingSystem.controller;

import com.alienlab.tracingSystem.Repository.DescItemRepository;
import com.alienlab.tracingSystem.service.DescItemService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by master on 2017/3/26.
 */
@Api(value="/api/descItem",description="详情Api")
public class DescItemController {
    @Autowired
    private DescItemService descItemService;
    private DescItemRepository descItemRepository;
}
