package com.alienlab.tracingSystem.service;

import com.alienlab.tracingSystem.Repository.User1Repository;
import com.alienlab.tracingSystem.entity.User;
import com.alienlab.tracingSystem.entity.User1;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by master on 2017/4/12.
 */
@Service
public class User1Service {
    @Autowired
    private User1Repository user1Repository;

    public User1 findUser1ByAccount(String account){
        return user1Repository.findUser1ByAccount(account);
    }
    public User1 addUser1(User1 user1){
        if(user1!=null){
            return user1Repository.save(user1);
        }else{
            return null;
        }
    }
}
