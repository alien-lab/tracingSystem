package com.alienlab.tracingSystem.service;

import com.alienlab.tracingSystem.Repository.BatchItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by master on 2017/4/6.
 */
@Service
public class BatchItemService {
    @Autowired
    private BatchItemRepository batchItemRepository;
}
