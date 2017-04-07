package com.alienlab.tracingSystem.controller;

import com.alienlab.tracingSystem.Repository.FarmItemRepository;
import com.alienlab.tracingSystem.db.ExecResult;
import com.alienlab.tracingSystem.entity.DescItem;
import com.alienlab.tracingSystem.entity.FarmInfo;
import com.alienlab.tracingSystem.entity.FarmItem;
import com.alienlab.tracingSystem.service.DescItemService;
import com.alienlab.tracingSystem.service.FarmInfoService;
import com.alienlab.tracingSystem.service.FarmItemService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by master on 2017/3/26.
 */
@Api(value="/api/farmItem",description="产品详情Api")
@RestController
@RequestMapping(value = "/farmctrl")
public class FarmItemController {
    @Autowired
    private FarmInfoService farmInfoService;
    @Autowired
    private FarmItemService farmItemService;
    @Autowired
    private DescItemService descItemService;
    @ApiOperation(value="添加农场的详情")
    @PostMapping(value="/items")
    public ResponseEntity addItem(@RequestParam Long itemid,@RequestParam Long farmid){
        FarmInfo farmInfo= farmInfoService.getFarmInfoById(farmid);
        DescItem descItem=descItemService.getDescItemById(itemid);
        FarmItem farmItem=new FarmItem();
        farmItem.setDescItemByDetailcontentId(descItem);
        farmItem.setFarmInfoByFarmInfoId(farmInfo);
        FarmItem result=farmItemService.addItem(farmItem);
        if(result==null){
            ExecResult er= new ExecResult(false,"添加农场详情出现异常");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(er);
        }
        else{
            ExecResult success= new ExecResult(true,"添加农场详情成功");
            return ResponseEntity.ok().body(success);
        }
    }
    @ApiOperation(value="显示该农场的详情信息")
    @GetMapping(value="/items/{farmid}")
    public List<FarmItem> getItems(@PathVariable Long farmid){
        return farmItemService.getItems(farmid);
    }
}
