package com.alienlab.tracingSystem.controller;

import com.alibaba.fastjson.JSON;
import com.alienlab.tracingSystem.db.ExecResult;
import com.alienlab.tracingSystem.entity.User;
import com.alienlab.tracingSystem.entity.User1;
import com.alienlab.tracingSystem.service.User1Service;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by master on 2017/4/12.
 */
@Api(value="/api/user",description="用户Api")
@RestController
@RequestMapping(value="/user1")
public class User1Controller {
    @Autowired
    private User1Service user1Service;
//用户登录
@ApiOperation(value="用户登录")
@PostMapping(value = "/dologin")
public String dologin(@RequestParam String account, @RequestParam String password, HttpServletRequest request){
    User1 user1=user1Service.findUser1ByAccount(account);
    System.out.println("user1"+user1);
    if(user1==null){
        return new ExecResult(false,"登录用户不存在").toString();
    }else{
        if(user1.getPassword().equals(password)){//登录成功

            request.getSession().setAttribute("user",user1);//当前用户进入session
            ExecResult er=new ExecResult();
            er.setResult(true);
            er.setData((JSON) com.alibaba.fastjson.JSON.toJSON(user1));
            return er.toString();
        }else{
            return new ExecResult(false,"用户名或密码错误").toString();
        }
    }
}
    @ApiOperation(value="用户注册")
    @PostMapping(value = "/doregister")
    public ResponseEntity addUser1(@RequestParam String loginname,@RequestParam String password){
        User1 user1=new User1();
        user1.setAccount(loginname);
        user1.setPassword(password);
        User1 user1_check=user1Service.findUser1ByAccount(loginname);
        if(user1_check==null){
            User1 result=user1Service.addUser1(user1);
            if(result==null){
                ExecResult er= new ExecResult(false,"用户的注册出现异常");
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(er);
            }
            else{
                ExecResult er = new ExecResult();
                er.setResult(true);
                er.setData((JSON) com.alibaba.fastjson.JSON.toJSON(result));
                return ResponseEntity.ok().body(er);
            }
        }else{
            ExecResult er= new ExecResult(false,"已经存在此用户名");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(er);
        }

    }
}
