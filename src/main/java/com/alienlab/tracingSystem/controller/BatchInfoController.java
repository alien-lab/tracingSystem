package com.alienlab.tracingSystem.controller;

import com.alibaba.fastjson.JSON;
import com.alienlab.tracingSystem.db.ExecResult;
import com.alienlab.tracingSystem.entity.BatchInfo;
import com.alienlab.tracingSystem.entity.FarmInfo;
import com.alienlab.tracingSystem.entity.ProductInfo;
import com.alienlab.tracingSystem.service.BatchInfoService;
import com.alienlab.tracingSystem.service.ProductInfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.sql.Timestamp;

/**
 * Created by master on 2017/4/6.
 */
@Api(value="/api/batchInfo",description="批次Api")
@RestController
@RequestMapping(value = "/batch")
public class BatchInfoController {
    @Autowired
    private BatchInfoService batchInfoService;
    @Autowired
    private ProductInfoService productInfoService;
    @ApiOperation(value="获取所有批次的信息")
    @RequestMapping(value = "/{index}-{size}",method = RequestMethod.GET)
    public Page<BatchInfo> allBatchs(@PathVariable("index") String index, @PathVariable("size") String size){
        return batchInfoService.getBatchPages(Integer.parseInt(index),Integer.parseInt(size));
    }
    @ApiOperation(value="添加批次的基本信息")
    @PostMapping(value = "/addBatch")
    public ResponseEntity addBatch(@RequestParam Long id, @RequestParam String batchString , @RequestParam String batchName,
                                   @RequestParam Integer batchNoStart, @RequestParam Integer batchNoEnd,
                                   @RequestParam long batchTotalCount,  @RequestParam String batchStatus){
        ProductInfo productInfo=productInfoService.getProductInfoById(id);
        BatchInfo batchInfo=new BatchInfo();
        batchInfo.setProductByProductId(productInfo);
        batchInfo.setBatchString(batchString);
        batchInfo.setBatchName(batchName);
        batchInfo.setBatchNoStart(batchNoStart);
        batchInfo.setBatchNoEnd(batchNoEnd);
        batchInfo.setBatchTotalCount(batchNoEnd-batchNoStart+1L);
        batchInfo.setBatchTime(new Timestamp(new Date().getTime()));
        batchInfo.setBatchStatus(batchStatus);
        batchInfo.setBatchLastTime(new Timestamp(new Date().getTime()));
        BatchInfo result=batchInfoService.addBatchInfo(batchInfo);

        if(result==null){
            ExecResult er= new ExecResult(false,"添加批次的基本信息出现异常");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(er);
        }
        else{
            ExecResult er = new ExecResult();
            er.setResult(true);
            er.setData((JSON) com.alibaba.fastjson.JSON.toJSON(result));
            return ResponseEntity.ok().body(er);
        }

    }

    @ApiOperation(value="删除某个批次的基本信息")
    @DeleteMapping(value="/deleteBatch/{id}")
    public ResponseEntity deleteBatch(@PathVariable("id") long id)  {
        boolean flag = false;
        try {
            flag = batchInfoService.deleteBatchInfoById(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (flag == true){
            ExecResult right=  new ExecResult(true,"删除批次基本信息成功！");
            return ResponseEntity.ok().body(right);
        }else {
            ExecResult er=  new ExecResult(false,"删除批次基本信息失败！请重试");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(er);
        }
    }
    @ApiOperation(value="更新批次的基本信息")
    @PostMapping(value = "/updateBatch")
    public ResponseEntity updateBatch(@RequestParam Long id, @RequestParam String batchString , @RequestParam String batchName,
                                      @RequestParam Integer batchNoStart, @RequestParam Integer batchNoEnd,
                                      @RequestParam long batchTotalCount,  @RequestParam String batchStatus){
        BatchInfo batchInfo=batchInfoService.getBatchInfoById(id);
        batchInfo.setBatchString(batchString);
        batchInfo.setBatchName(batchName);
        batchInfo.setBatchNoStart(batchNoStart);
        batchInfo.setBatchNoEnd(batchNoEnd);
        batchInfo.setBatchTotalCount(batchNoEnd-batchNoStart+1L);
       /* batchInfo.setBatchTime(new Timestamp(new Date().getTime()));*/
        batchInfo.setBatchStatus(batchStatus);
        batchInfo.setBatchLastTime(new Timestamp(new Date().getTime()));
        BatchInfo result=batchInfoService.updateBatchInfo(batchInfo);
        if(result==null){
            ExecResult er= new ExecResult(false,"更新批次基本信息出现异常");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(er);
        }
        else{
            ExecResult er = new ExecResult();
            er.setResult(true);
            er.setData((JSON) com.alibaba.fastjson.JSON.toJSON(result));
            return ResponseEntity.ok().body(er);
        }
    }

}
