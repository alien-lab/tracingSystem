package com.alienlab.tracingSystem.controller;

import com.alibaba.fastjson.JSON;
import com.alienlab.tracingSystem.Repository.AdminRepository;
import com.alienlab.tracingSystem.db.ExecResult;
import com.alienlab.tracingSystem.entity.Admin;
import com.alienlab.tracingSystem.service.AdminService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by master on 2017/5/13.
 */
@Api(value="/api/admin",description="管理员Api")
@RestController
@RequestMapping(value="/admin")
public class AdminController {
    @Autowired
    private AdminService adminService;
    @Autowired
    private AdminRepository adminRepository;

    //管理员登录
    @ApiOperation(value="管理员登录")
    @PostMapping(value = "/dologin")
    public String dologin(@RequestParam String loginname, @RequestParam String password, HttpServletRequest request){
        Admin admin=adminService.findAdminByLoginname(loginname);
        System.out.println("admin"+admin);
        if(admin==null){
            return new ExecResult(false,"登录管理员不存在").toString();
        }else{
            if(admin.getPassword().equals(password)){//登录成功
                request.getSession().setAttribute("admin",admin);//当前用户进入session
                ExecResult er=new ExecResult();
                er.setResult(true);
                er.setData((JSON) JSON.toJSON(admin));
                return er.toString();
            }else{
                return new ExecResult(false,"用户名/密码错误").toString();
            }
        }
    }
}
