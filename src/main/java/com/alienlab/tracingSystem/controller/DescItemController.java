package com.alienlab.tracingSystem.controller;

import ch.qos.logback.core.net.SyslogOutputStream;
import com.alibaba.fastjson.JSONObject;
import com.alienlab.tracingSystem.Repository.DescItemRepository;
import com.alienlab.tracingSystem.db.ExecResult;
import com.alienlab.tracingSystem.entity.DescItem;
import com.alienlab.tracingSystem.entity.FarmInfo;
import com.alienlab.tracingSystem.service.DescItemService;
import com.alienlab.tracingSystem.service.PicService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

import static com.alibaba.fastjson.JSON.parseObject;

/**
 * Created by master on 2017/3/26.
 */
@Api(value="/api/descItem",description="详情Api")
@RestController
@RequestMapping(value = "/desc")
public class DescItemController {
    @Autowired
    private DescItemService descItemService;
    @Autowired
    ServletContext context;
    //添加详情
    @ApiOperation(value="添加详情")
    @PostMapping(value="/addDesc")
    public DescItem addDesc(@RequestParam String type,@RequestParam String text,@RequestParam String link,
                            @RequestParam String label ,@RequestParam String file){
        PicService service=new PicService();
        String head = "";
        Timestamp ipTimeStamp = new Timestamp(new Date().getTime());// 获取时间戳+随机IP
        if (type.equals("image")) {
            System.out.println("hahhah"+type+text+link+label+file);
            SimpleDateFormat format = new SimpleDateFormat("yyMMddHHmmss");
            String timeStamp = format.format(ipTimeStamp);
            String path=context.getRealPath("/uploadimages");
            String filename=service.base64ToImage(file,path);
            System.out.println("uuuu"+filename);
            DescItem descItem = new DescItem("image",label,text,filename,"jpg",link,ipTimeStamp,1);
            System.out.println(descItem);
            descItem= descItemService.addDesc(descItem);
            return descItem;
        }else{
            DescItem descItem = new DescItem("text",label,text,"","",link,ipTimeStamp,1);
            System.out.println("ahah"+descItem);
            descItem= descItemService.addDesc(descItem);
            return descItem;
        }
        /* if (type.equals("image")) {
            SimpleDateFormat format = new SimpleDateFormat("yyMMddHHmmss");
            String timeStamp = format.format(ipTimeStamp);
            String path=context.getRealPath("/uploadimages");
            String filename=service.base64ToImage(file,path);
            System.out.println(filename);
            DescItem descItem = new DescItem("image",label,text,filename,"jpg",link,ipTimeStamp,1);
            System.out.println("youyou"+descItem);
            descItem= descItemService.addDesc(descItem);
            if(descItem==null){
                ExecResult er= new ExecResult(false,"添加详情信息出现异常");
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(er);
            }
            else{
                ExecResult success= new ExecResult(true,"成功添加一条详情信息");
                return ResponseEntity.ok().body(success);
            }
        }else{
            DescItem descItem = new DescItem("text",label,text,"","",link,ipTimeStamp,1);
            System.out.println(descItem);
            descItem= descItemService.addDesc(descItem);
            if(descItem==null){
                ExecResult er= new ExecResult(false,"添加详情出现异常");
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(er);
            }
            else{
                ExecResult success= new ExecResult(true,"成功添加一条详情");
                return ResponseEntity.ok().body(success);
            }
        }*/
    }
    //删除某个详情
    @ApiOperation(value="删除详情")
    @PostMapping(value="/deleteDesc")
    public boolean deleteDesc(@RequestParam Long itemid) throws IOException {
        return descItemService.deleteDesc(itemid);
    }
    //更新详情状态
    @ApiOperation(value="更新详情")
    @PostMapping(value="/updateDesc")
    public ResponseEntity updateDesc( @RequestParam Long itemid,@RequestParam String status){
        DescItem descItem=descItemService.getDescItemById(itemid);
        descItem.setItemStatus(status);
        DescItem result=descItemService.addDesc(descItem);
        if(result==null){
            ExecResult er= new ExecResult(false,"更新详情信息出现异常");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(er);
        }
        else{
            ExecResult success= new ExecResult(true,"更新详情信息成功");
            return ResponseEntity.ok().body(success);
        }
    }
}
