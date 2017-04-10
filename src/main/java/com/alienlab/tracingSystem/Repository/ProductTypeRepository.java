package com.alienlab.tracingSystem.Repository;

import com.alienlab.tracingSystem.entity.ProductType;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by master on 2017/4/8.
 */
public interface ProductTypeRepository extends JpaRepository<ProductType,Long>{
}
