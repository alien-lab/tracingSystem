package com.alienlab.tracingSystem.service;

import com.alienlab.tracingSystem.Repository.AdminRepository;
import com.alienlab.tracingSystem.entity.Admin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by master on 2017/5/13.
 */
@Service
public class AdminService {
    @Autowired
    private AdminRepository adminRepository;
    public Admin findAdminByLoginname(String loginname){
        return adminRepository.findAdminByLoginname(loginname);
    }
    public Admin addAdmin(Admin admin){
        if(admin!=null){
            return adminRepository.save(admin);
        }else{
            return null;
        }
    }
}
