package com.alienlab.tracingSystem.controller;

import com.alienlab.tracingSystem.db.ExecResult;
import com.alienlab.tracingSystem.entity.*;
import com.alienlab.tracingSystem.service.DescItemService;
import com.alienlab.tracingSystem.service.ProductInfoService;
import com.alienlab.tracingSystem.service.ProductItemService;
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
@Api(value="/api/productItem",description="产品详情Api")
@RestController
@RequestMapping(value = "/product")
public class ProductItemController {
    @Autowired
    private ProductInfoService productInfoService;
    @Autowired
    private ProductItemService productItemService;
    @Autowired
    private DescItemService descItemService;
    @ApiOperation(value="添加产品详情信息")
    @PostMapping(value="/items/add")
    public ResponseEntity addItem(@RequestParam Long descid, @RequestParam Long pid){
        ProductInfo productInfo= productInfoService.getProductInfoById(pid);
        DescItem descItem=descItemService.getDescItemById(descid);
        ProductItem productItem=new ProductItem();
        productItem.setDescItemByDetailcontentId(descItem);
        productItem.setProductInfoByProductInfoId(productInfo);
        ProductItem result=productItemService.addDesc(productItem);
        if(result==null){
            ExecResult er= new ExecResult(false,"添加产品详情出现异常");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(er);
        }
        else{
            ExecResult success= new ExecResult(true,"添加产品详情成功");
            return ResponseEntity.ok().body(success);
        }
    }
    @ApiOperation(value="显示该产品的详情信息")
    @PostMapping(value="/items")
    public List<ProductItem> getItems(@RequestParam Long pid){
        return productItemService.getItems(pid);
    }
}
