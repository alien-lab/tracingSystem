package com.alienlab.tracingSystem.controller;

import com.alienlab.tracingSystem.db.ExecResult;
import com.alienlab.tracingSystem.entity.*;
import com.alienlab.tracingSystem.service.BatchInfoService;
import com.alienlab.tracingSystem.service.BatchItemService;
import com.alienlab.tracingSystem.service.DescItemService;
import com.alienlab.tracingSystem.service.ProductInfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by master on 2017/4/6.
 */
@Api(value="/api/batchItem",description="批次详情Api")
@RestController
@RequestMapping(value = "/batch")
public class BatchItemController {
    @Autowired
    private BatchInfoService batchInfoService;
    @Autowired
    private BatchItemService batchItemService;
    @Autowired
    private DescItemService descItemService;

    @ApiOperation(value="添加批次的详情信息")
    @PostMapping(value="/items")
    public ResponseEntity addItem(@RequestParam Long descid, @RequestParam Long batchid){
        BatchInfo batchInfo= batchInfoService.getBatchInfoById(batchid);
        DescItem descItem=descItemService.getDescItemById(descid);
        BatchItem batchItem=new BatchItem();
        batchItem.setDetailContentId(descItem);
        batchItem.setBatchInfoId(batchInfo);
        BatchItem result=batchItemService.addDesc(batchItem);
        if(result==null){
            ExecResult er= new ExecResult(false,"添加批次详情出现异常");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(er);
        }
        else{
            ExecResult success= new ExecResult(true,"添加批次详情成功");
            return ResponseEntity.ok().body(success);
        }
    }
    @ApiOperation(value="显示该批次的详情信息")
    @GetMapping(value="/items/{batchid}")
    public List<BatchItem> getItems(@PathVariable Long batchid){
        return batchItemService.getItems(batchid);
    }
}
