package com.alienlab.tracingSystem.service;

import com.alienlab.tracingSystem.Repository.ProductTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by master on 2017/4/8.
 */
@Service
public class ProductTypeService {
    @Autowired
    private ProductTypeRepository productTypeRepository;
}
