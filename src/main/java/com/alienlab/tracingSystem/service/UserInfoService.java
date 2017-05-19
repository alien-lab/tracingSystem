package com.alienlab.tracingSystem.service;

import com.alienlab.tracingSystem.Repository.UserInfoRepository;
import com.alienlab.tracingSystem.entity.FarmInfo;
import com.alienlab.tracingSystem.entity.User;
import com.alienlab.tracingSystem.entity.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by master on 2017/5/13.
 */
@Service
public class UserInfoService {
    @Autowired
    private UserInfoRepository userInfoRepository;
    public UserInfo findUserInfoByAccount(String account){
        return userInfoRepository.findUserInfoByAccount(account);
    }
    public UserInfo addUserInfo(UserInfo userInfo){
        if(userInfo!=null){
            return userInfoRepository.save(userInfo);
        }else{
            return null;
        }
    }
    public UserInfo updateUserInfo(UserInfo userInfo){
        try {
            userInfo = userInfoRepository.save(userInfo);
            return userInfo;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
}
