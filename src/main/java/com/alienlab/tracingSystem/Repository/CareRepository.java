package com.alienlab.tracingSystem.Repository;

import com.alienlab.tracingSystem.entity.Care;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by master on 2017/5/15.
 */
@Repository
public interface CareRepository extends JpaRepository<Care,Long>{
    List<Care> findByAccount(String account);

    Care findByFarmIdAndAccount(long id, String account);
}
