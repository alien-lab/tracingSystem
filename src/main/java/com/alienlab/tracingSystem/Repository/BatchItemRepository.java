package com.alienlab.tracingSystem.Repository;

import com.alienlab.tracingSystem.entity.BatchItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by master on 2017/4/6.
 */
@Repository
public interface BatchItemRepository extends JpaRepository<BatchItem,Long> {
}
