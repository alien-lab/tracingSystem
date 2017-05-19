package com.alienlab.tracingSystem.Repository;
import com.alienlab.tracingSystem.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by master on 2017/5/13.
 */
@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    User findUserByAccount(String account);
}
