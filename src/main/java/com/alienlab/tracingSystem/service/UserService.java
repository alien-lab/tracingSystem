package com.alienlab.tracingSystem.service;

import com.alienlab.tracingSystem.Repository.UserRepository;
import com.alienlab.tracingSystem.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by master on 2017/3/26.
 */
@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    //通过用户名查找用户
    public User findUserByLoginname(String loginname){
        return userRepository.findUserByLoginname(loginname);
    }

}
