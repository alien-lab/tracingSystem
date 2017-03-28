package com.alienlab.tracingSystem.Repository;

import com.alienlab.tracingSystem.entity.FarmInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by master on 2017/3/26.
 */
@Repository
public interface FarmInfoRepository extends JpaRepository<FarmInfo,Long>{
}
