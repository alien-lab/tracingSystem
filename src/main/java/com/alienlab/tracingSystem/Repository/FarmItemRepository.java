package com.alienlab.tracingSystem.Repository;

import com.alienlab.tracingSystem.entity.DescItem;
import com.alienlab.tracingSystem.entity.FarmInfo;
import com.alienlab.tracingSystem.entity.FarmItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by master on 2017/3/26.
 */
@Repository
public interface FarmItemRepository extends JpaRepository<FarmItem,Long>{
    List<FarmItem> findFarmItemsByFarmInfoByFarmInfoIdOrderByIdDesc(FarmInfo farmInfo);
    @Transactional
    void deleteByDescItemByDetailcontentId(DescItem item);
}
