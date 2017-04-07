package com.alienlab.tracingSystem.Repository;

import com.alienlab.tracingSystem.entity.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by master on 2017/4/6.
 */
@Repository
public interface BatchItemRepository extends JpaRepository<BatchItem,Long> {
    List<BatchItem> findBatchItemByBatchInfoIdOrderByIdDesc(BatchInfo batchInfoId);
   // BatchItem findBatchItemsByDetailContentId(DescItem detailContentId);
    @Transactional
    void deleteItemByDetailContentId(DescItem item);
}
