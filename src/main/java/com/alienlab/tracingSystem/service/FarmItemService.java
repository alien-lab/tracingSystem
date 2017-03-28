package com.alienlab.tracingSystem.service;

import com.alienlab.tracingSystem.Repository.FarmItemRepository;
import com.alienlab.tracingSystem.entity.FarmItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by master on 2017/3/26.
 */
@Service
public class FarmItemService {
    @Autowired
    private FarmItemRepository farmItemRepository;
}
