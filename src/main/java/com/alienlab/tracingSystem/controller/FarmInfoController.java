package com.alienlab.tracingSystem.controller;

import com.alibaba.fastjson.JSON;
import com.alienlab.tracingSystem.Repository.FarmInfoRepository;
import com.alienlab.tracingSystem.db.ExecResult;
import com.alienlab.tracingSystem.entity.FarmInfo;
import com.alienlab.tracingSystem.service.FarmInfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Created by master on 2017/3/26.
 */
@Api(value="/api/farmInfos",description="农场信息Api")
@RestController
@RequestMapping(value = "/farmctrl")
public class FarmInfoController {
    @Autowired
    private FarmInfoService farmInfoService;
    @Autowired
    private FarmInfoRepository farmInfoRepository;
    @ApiOperation(value="获取所有农场信息")
    @RequestMapping(value="/{index}-{size}", method=RequestMethod.GET)
    public Page<FarmInfo> allfarms(@PathVariable("index") String index, @PathVariable("size") String size){
        return farmInfoService.getFarmsPage(Integer.parseInt(index),Integer.parseInt(size));
    }
    @ApiOperation(value="添加农场的基本信息")
    @PostMapping(value = "/addFarm")
    public ResponseEntity addFarm( @RequestParam String farmName){
             System.out.println("后台的农场名称"+farmName);
             FarmInfo farmInfo=new FarmInfo();
             farmInfo.setFarmName(farmName);

             FarmInfo result=farmInfoService.addFarm(farmInfo);
             if(result==null){
                 ExecResult er= new ExecResult(false,"添加农场信息出现异常");
                 return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(er);
             }
             else{
                 ExecResult er = new ExecResult();
                 er.setResult(true);
                 er.setData((JSON) com.alibaba.fastjson.JSON.toJSON(result));
                 return ResponseEntity.ok().body(er);
             }
    }
    @ApiOperation(value="删除某个农场基本信息")
    @DeleteMapping(value="/deleteFarm/{farmid}")
    public ResponseEntity deleteFarm( @PathVariable("farmid") Long farmid)  {
        boolean flag = false;
        try {
            flag = farmInfoService.deleteFarm(farmid);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (flag == true){
            ExecResult right=  new ExecResult(true,"删除农场基本信息成功！");
            return ResponseEntity.ok().body(right);
        }else {
            ExecResult er=  new ExecResult(false,"删除农场基本信息失败！请重试");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(er);
        }
    }
    @ApiOperation(value="更新农场的基本信息")
    @PostMapping(value = "/updateFarm")
    public ResponseEntity updateFarm( @RequestParam Long farmid,@RequestParam String farmName){
        FarmInfo farmInfo=new FarmInfo();
        farmInfo.setFarmName(farmName);
        farmInfo.setId(farmid);
        FarmInfo result=farmInfoService.updateFarm(farmInfo);
        if(result==null){
            ExecResult er= new ExecResult(false,"更新农场信息出现异常");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(er);
        }
        else{
            ExecResult er = new ExecResult();
            er.setResult(true);
            er.setData((JSON) com.alibaba.fastjson.JSON.toJSON(result));
            return ResponseEntity.ok().body(er);
        }
    }
    /*@GetMapping(value = "/addFarm")
    public ResponseEntity addFarm(){
        return ResponseEntity.ok().body("success");
    }*/
}
