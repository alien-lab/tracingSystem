package com.alienlab.tracingSystem.controller;

import com.alibaba.fastjson.JSON;
import com.alienlab.tracingSystem.db.ExecResult;
import com.alienlab.tracingSystem.entity.BatchInfo;
import com.alienlab.tracingSystem.service.GetBatchService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by master on 2017/4/11.
 */
@Api(value="/api/getBatch",description="批次查询Api")
@RestController
@RequestMapping(value = "/batch")
public class GetBatchController {
    @Autowired
    private GetBatchService getBatchService;
    @ApiOperation(value="获取批次的基本信息")
    @GetMapping(value = "/batchInfo/{batchString}")
    public ResponseEntity getBatch(@PathVariable("batchString") String batchString){
       BatchInfo result=getBatchService.findBatchInfoByBatchString(batchString);
        if(result==null){
            ExecResult er= new ExecResult(false,"查询批次的基本信息出现异常");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(er);
        }
        else{
            ExecResult er = new ExecResult();
            er.setResult(true);
            er.setData((JSON) com.alibaba.fastjson.JSON.toJSON(result));
            return ResponseEntity.ok().body(er);
        }
    }

    @ApiOperation(value="获取批次的详情")
    @GetMapping(value = "/batchDesc/{batchString}")
    public ResponseEntity getBatchDesc(@PathVariable("batchString") String batchString){
        List results=getBatchService.findDescItemByBatchString(batchString);
        if(results==null){
            ExecResult er= new ExecResult(false,"查询批次的详情出现异常");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(er);
        }
        else{
            ExecResult er = new ExecResult();
            er.setResult(true);
            er.setData((JSON) com.alibaba.fastjson.JSON.toJSON(results));
            return ResponseEntity.ok().body(er);
        }
    }
    @ApiOperation(value="获取该批次对应产品的详情")
    @GetMapping(value = "/productDesc/{batchString}")
    public ResponseEntity getProductDesc(@PathVariable("batchString") String batchString){
        List results=getBatchService.findProductDescByBatchString(batchString);
        if(results==null){
            ExecResult er= new ExecResult(false,"查询该批次对应产品的详情出现异常");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(er);
        }
        else{
            ExecResult er = new ExecResult();
            er.setResult(true);
            er.setData((JSON) com.alibaba.fastjson.JSON.toJSON(results));
            return ResponseEntity.ok().body(er);
        }
    }
    @ApiOperation(value="获取该批次对应产品的详情")
    @GetMapping(value = "/farmDesc/{batchString}")
    public ResponseEntity getFarmDesc(@PathVariable("batchString") String batchString){
        List results=getBatchService.findFarmDescByBatchString(batchString);
        if(results==null){
            ExecResult er= new ExecResult(false,"查询该批次对应产品的农场的详情出现异常");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(er);
        }
        else{
            ExecResult er = new ExecResult();
            er.setResult(true);
            er.setData((JSON) com.alibaba.fastjson.JSON.toJSON(results));
            return ResponseEntity.ok().body(er);
        }
    }
}
