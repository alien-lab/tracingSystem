package com.alienlab.tracingSystem.controller;


import com.alienlab.tracingSystem.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by master on 2017/5/13.
 */
@Api(value="/api/user",description="用户Api")
@RestController
@RequestMapping(value="/user")
public class UserController {
    @Autowired
    private UserService userService;
    @ApiOperation(value="用户注册")
    @PostMapping(value = "/regCheck")
    public ResponseEntity addUser(@RequestParam String username, @RequestParam String password1,@RequestParam String password2){
         System.out.println("lalal啦"+username);
        return null;
        /*User1 user1=new User1();
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
        }*/

    }
}
