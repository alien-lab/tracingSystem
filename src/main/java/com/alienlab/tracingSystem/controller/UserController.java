package com.alienlab.tracingSystem.controller;

import com.alibaba.fastjson.JSON;
import com.alienlab.tracingSystem.Repository.UserRepository;
import com.alienlab.tracingSystem.db.ExecResult;
import com.alienlab.tracingSystem.entity.User;
import com.alienlab.tracingSystem.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by master on 2017/3/26.
 */
@Api(value="/api/user",description="管理员Api")
@RestController
@RequestMapping(value="/user")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private UserRepository userRepository;

    //管理员登录
    @ApiOperation(value="用户登录")
    @PostMapping(value = "/dologin")
    public ResponseEntity dologin(@RequestParam String loginname,@RequestParam String password,HttpServletRequest request){
        User user=userService.findUserByLoginname(loginname);
        System.out.println(user);
        if(user==null){
            ExecResult er= new ExecResult(false,"登录用户不存在");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(er);

        }else{
            if(user.getPassword().equals(password)){//登录成功
                request.getSession().setAttribute("user",user);//当前用户进入session
                return ResponseEntity.ok().body(user);
            }else{
                ExecResult er=  new ExecResult(false,"用户名或密码错误");
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(er);
            }
        }
    }
    @ApiOperation(value="用户注册")
    @PostMapping(value = "/doregister")
    public ResponseEntity addUser(@RequestParam String loginname,@RequestParam String password){
        User user=new User();
        user.setLoginname(loginname);
        user.setPassword(password);
        user.setUserstatus("1");
        User user_check=userRepository.findUserByLoginname(loginname);
        if(user_check==null){
            User result=userService.addUser(user);
            if(result==null){
                ExecResult er= new ExecResult(false,"用户注册出现异常");
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
