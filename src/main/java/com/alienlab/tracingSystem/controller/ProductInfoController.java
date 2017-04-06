package com.alienlab.tracingSystem.controller;

import com.alibaba.fastjson.JSON;
import com.alienlab.tracingSystem.db.ExecResult;
import com.alienlab.tracingSystem.entity.FarmInfo;
import com.alienlab.tracingSystem.entity.ProductInfo;
import com.alienlab.tracingSystem.service.FarmInfoService;
import com.alienlab.tracingSystem.service.ProductInfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Created by master on 2017/4/6.
 */
@Api(value="/api/productInfo",description="产品Api")
@RestController
@RequestMapping(value = "/product")
public class ProductInfoController {
    @Autowired
    private ProductInfoService productInfoService;
    @Autowired
    private FarmInfoService farmInfoService;
    @ApiOperation(value="获取所有产品的信息")
    @RequestMapping(value="/{index}-{size}", method= RequestMethod.GET)
    public Page<ProductInfo> allproducts(@PathVariable("index")String index, @PathVariable("size")String size){
        return productInfoService.getProductsPage(Integer.parseInt(index),Integer.parseInt(size));
    }
    @ApiOperation(value="添加产品的基本信息")
    @PostMapping(value = "/add")
    public ResponseEntity addProduct(@RequestParam String productName,@RequestParam String productDesc ,@RequestParam Double productPrice,
                                     @RequestParam String productFlag,@RequestParam long farmid,@RequestParam String status){
        FarmInfo farmInfo=farmInfoService.getFarmInfoById(farmid);
        ProductInfo productInfo=new ProductInfo();
        productInfo.setFarmNameByFarmId(farmInfo);
        productInfo.setProductStatus(status);
        ProductInfo result=productInfoService.addProduct(productInfo);
        if(result==null){
            ExecResult er= new ExecResult(false,"添加产品的基本信息出现异常");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(er);
        }
        else{
            ExecResult er = new ExecResult();
            er.setResult(true);
            er.setData((JSON) com.alibaba.fastjson.JSON.toJSON(result));
            return ResponseEntity.ok().body(er);
        }

    }
    @ApiOperation(value="删除某个产品的基本信息")
    @DeleteMapping(value="/delete/{id}")
    public ResponseEntity deleteProduct(@PathVariable("id") long id)  {
        boolean flag = false;
        try {
            flag = productInfoService.deleteProduct(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (flag == true){
            ExecResult right=  new ExecResult(true,"删除产品基本信息成功！");
            return ResponseEntity.ok().body(right);
        }else {
            ExecResult er=  new ExecResult(false,"删除产品基本信息失败！请重试");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(er);
        }
    }
    @ApiOperation(value="更新产品的基本信息")
    @PostMapping(value = "/update")
    public ResponseEntity updateProduct(@RequestParam long id, @RequestParam String productName,@RequestParam String productDesc ,@RequestParam Double productPrice,
                                         @RequestParam String productFlag,@RequestParam long farmid,@RequestParam String status){
        FarmInfo farmInfo=farmInfoService.getFarmInfoById(farmid);
        ProductInfo productInfo=new ProductInfo();
        productInfo.setFarmNameByFarmId(farmInfo);
        productInfo.setProductFlag(productFlag);
        ProductInfo result=productInfoService.updateProduct(productInfo);
        if(result==null){
            ExecResult er= new ExecResult(false,"更新产品基本信息出现异常");
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
