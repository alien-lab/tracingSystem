package com.alienlab.tracingSystem.Repository;

import com.alienlab.tracingSystem.entity.Care;
import com.alienlab.tracingSystem.entity.Collect;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by master on 2017/5/21.
 */
@Repository
public interface CollectRepostory extends JpaRepository<Collect,Long> {
    List<Collect> findByAccount(String account);

    Collect findByProductIdAndAccount(long id, String account);
}
