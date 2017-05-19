package com.alienlab.tracingSystem.service;
import com.alienlab.tracingSystem.Repository.UserRepository;
import com.alienlab.tracingSystem.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by master on 2017/5/13.
 */
@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public User findUserByAccount(String account){
        return userRepository.findUserByAccount(account);
    }
    public User addUser(User user){
        if(user!=null){
            return userRepository.save(user);
        }else{
            return null;
        }
    }
}
