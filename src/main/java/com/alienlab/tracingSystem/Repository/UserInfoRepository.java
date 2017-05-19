package com.alienlab.tracingSystem.Repository;

import com.alienlab.tracingSystem.entity.User;
import com.alienlab.tracingSystem.entity.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by master on 2017/5/13.
 */
@Repository
public interface UserInfoRepository extends JpaRepository<UserInfo,Long>{
    UserInfo findUserInfoByAccount(String account);
}
