package com.alienlab.tracingSystem.Repository;

import com.alienlab.tracingSystem.entity.User;
import com.alienlab.tracingSystem.entity.User1;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by master on 2017/4/12.
 */
public interface User1Repository extends JpaRepository<User1,Long> {
    User1 findUser1ByAccount(String account);
}
