package com.alienlab.tracingSystem.controller;

import com.alienlab.tracingSystem.db.ExecResult;
import com.alienlab.tracingSystem.entity.*;
import com.alienlab.tracingSystem.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by master on 2017/5/14.
 */
@Controller
public class QianController {
    @Autowired
    private UserService userService;
    @Autowired
    private UserInfoService userInfoService;
    @Autowired
    private FarmInfoService farmInfoService;
    @Autowired
    private ProductInfoService productInfoService;
    @Autowired
    private ProductTypeService productTypeService;
    @Autowired
    private GetBatchService getBatchService;
    @Autowired
    private  CareService careService;
    @Autowired
    private CollectService collectService;
    @Autowired
    private FarmItemService farmItemService;
    @Autowired
    private  ProductItemService productItemService;
    @RequestMapping("index")
    public String index(Model model){
        List<FarmInfo> farmInfos=farmInfoService.getFarms();
        List<ProductInfo> productInfos=productInfoService.getProducts();
        model.addAttribute("farmInfos",farmInfos);
        model.addAttribute("productInfos",productInfos);
        return "index";
    }
    @RequestMapping("register")
    public String register(){
        return "register";
    }
    @RequestMapping(value = "regCheck", method = RequestMethod.POST)
    public String  regCheck(HttpServletRequest request, HttpServletResponse response)throws IOException {
        String account=request.getParameter("account");
        String password=request.getParameter("password");
        String password2=request.getParameter("password2");
        User user=new User();
        UserInfo userInfo=new UserInfo();
        if(password.equals(password2)){
            user.setAccount(account);
            user.setPassword(password);
            User user_check= userService.addUser(user);
            userInfo.setAccount(account);
            userInfo.setHead("primary.jpg");
            UserInfo userInfo_check=userInfoService.addUserInfo(userInfo);
            if(user_check!=null && userInfo_check!=null){
                return "login";
            } else {
                return "register";
            }
        }else{
            ExecResult er= new ExecResult(false,"两次输入密码有误");
            return er.toString();
        }
    }
    @RequestMapping("login")
    public String login(){
        return "login";
    }
    @RequestMapping(value="loginCheck",method=RequestMethod.POST)
    public ModelAndView loginCheck(HttpServletRequest request){
        String account=request.getParameter("account");
        String password=request.getParameter("password");
        User user=userService.findUserByAccount(account);
        ModelAndView mav=null;
        if(user.getPassword().equals(password)){
            UserInfo userInfo=userInfoService.findUserInfoByAccount(account);
            request.getSession().setAttribute("userInfo",userInfo);
            mav = new ModelAndView("user_index");
            List<Care> cares=careService.findCaresByAccount(account);
            System.out.println("cares"+cares.size());
            List<FarmInfo> farmInfos=farmInfoService.getFarms();
            Map<FarmInfo,Boolean> map = new HashMap<>();
            for (FarmInfo f : farmInfos) {
                Boolean flag =  careService.checkIfCare(f.getId(),account);
                map.put(f,flag);
            }
            List<ProductInfo> productInfos=productInfoService.getProducts();
            Map<ProductInfo,Boolean> productsMap=new HashMap<>();
            for(ProductInfo p:productInfos){
                Boolean flag=collectService.checkIfCollect(p.getId(),account);
                productsMap.put(p,flag);
            }
            mav.addObject("largeMap",map);
            mav.addObject("productsMap",productsMap);
            mav.addObject("cares",cares);
        }
        return mav;
    }
    @RequestMapping("allFarm")
    public String allFarm(Model model,HttpServletRequest request){
        List<FarmInfo> farmInfos=farmInfoService.getFarms();
        model.addAttribute("farmInfos",farmInfos);
        String account = ((UserInfo)request.getSession().getAttribute("userInfo")).getAccount();
        return "allFarm";
    }
    @RequestMapping("user_index")
    public ModelAndView user_index(HttpServletRequest request){
        String account=((UserInfo)request.getSession().getAttribute("userInfo")).getAccount();
        ModelAndView mav=new ModelAndView("user_index");
        List<Care> cares=careService.findCaresByAccount(account);
        System.out.println("cares"+cares.size());
        List<FarmInfo> farmInfos=farmInfoService.getFarms();
        Map<FarmInfo,Boolean> map = new HashMap<>();
        for (FarmInfo f : farmInfos) {
            Boolean flag =  careService.checkIfCare(f.getId(),account);
            map.put(f,flag);
        }
        List<ProductInfo> productInfos=productInfoService.getProducts();
        Map<ProductInfo,Boolean> productsMap=new HashMap<>();
        for(ProductInfo p:productInfos){
            Boolean flag=collectService.checkIfCollect(p.getId(),account);
            productsMap.put(p,flag);
        }
        mav.addObject("largeMap",map);
        mav.addObject("productsMap",productsMap);
        mav.addObject("cares",cares);
        return mav;

    }
    @RequestMapping("allProducts")
    public String allProduct(Model model,HttpServletRequest request){
        List<ProductInfo> productInfos=productInfoService.getProducts();
        String account = ((UserInfo)request.getSession().getAttribute("userInfo")).getAccount();
        model.addAttribute("productInfos",productInfos);
        return "allProducts";
    }
    /*@RequestMapping("farmDesc")
    public String farmDesc(){
        return "farmDesc";
    }*/
    @RequestMapping(value = "getItem", method = RequestMethod.GET)
    public ModelAndView messages(HttpServletRequest request) {
        String batchString=request.getParameter("batchString");
        String name=batchString.split("-")[0];
        String number=batchString.split("-")[1];
        String orderNumber=batchString.split("-")[2];
        String batchNumber=name+"-"+number;
        System.out.println("batchNumber"+batchNumber);
        BatchInfo batchInfo=getBatchService.findBatchInfoByBatchString(batchNumber);
        List<DescItem> batchItems=getBatchService.findDescItemByBatchString(batchNumber);
        List<DescItem> productItems=getBatchService.findProductDescByBatchString(batchNumber);
        List<DescItem> farmItems=getBatchService.findFarmDescByBatchString(batchNumber);
        System.out.println("farmItems"+farmItems.size());
        System.out.println("batchItems"+batchItems.size());
        if(batchInfo==null){
            ModelAndView mav = new ModelAndView("getItemError");
            mav.addObject("batchString",batchString);
            return mav;
        }else{
            ModelAndView mav = new ModelAndView("getItem");
            mav.addObject("batchString",batchString);
            mav.addObject("orderNumber",orderNumber);
            mav.addObject("batchInfo", batchInfo);
            mav.addObject("productItems",productItems);
            mav.addObject("farmItems",farmItems);
            mav.addObject("batchItems",batchItems);
            return mav;
        }
    }
    @RequestMapping("personal")
    public ModelAndView personal(HttpServletRequest request){
        ModelAndView mav = new ModelAndView("personal");
        String account = ((UserInfo)request.getSession().getAttribute("userInfo")).getAccount();
        return mav;
    }
    @RequestMapping(value = "uploadHead",method = RequestMethod.POST)
    public String uploadHead(HttpServletRequest request ,@RequestParam("file") MultipartFile file) throws Exception{
        UserInfo userInfo=(UserInfo)request.getSession().getAttribute("userInfo");//从session中取出当前用户的信息
        if(!file.isEmpty()){//判断是否选中了一张图片
            String filePath=request.getSession().getServletContext().getRealPath("/")+"/head/" +file.getOriginalFilename();//获取服务器端的绝对路径+项目的相对路径head/图片原名
            file.transferTo(new File(filePath));//将客户端文件传输到服务器端！！！
            int position=filePath.lastIndexOf("/");//定位到D：\JavaEEPublish\question_Spring/head/1.jpg
            String head=filePath.substring(position+1);//获取真正的图片名，如“1.png”
            userInfo.setHead(head);//更新头像信息
        }
        userInfoService.updateUserInfo(userInfo);//修改用户信息
        request.getSession().setAttribute("userInfo", userInfo);
        return "redirect:personal.do";//路径重定向
    }
    @RequestMapping(value="modifyUserInfo" ,method=RequestMethod.POST)
    public String modifyUser(HttpServletRequest request,String nickname,String phone, String address){
        UserInfo userInfo=(UserInfo)request.getSession().getAttribute("userInfo");//取出原有的用户信息
        userInfo.setNickname(nickname);userInfo.setPhone(phone);userInfo.setAddress(address);
        userInfoService.updateUserInfo(userInfo);//调用服务层的修改信息方法
        request.getSession().setAttribute("userInfo", userInfo);//更新session信息
        return "redirect:personal.do";//controller重定向
    }
    @RequestMapping(value="care",method = RequestMethod.POST)
    @ResponseBody
    public String Care(HttpServletRequest request,HttpServletResponse response,Long farm_id) throws IOException{
        UserInfo userInfo=(UserInfo)request.getSession().getAttribute("userInfo");
        Care care=new Care(farm_id,userInfo.getAccount());
        careService.addCares(care);//调用关注农场功能
        return "关注成功!!!";//通过out向客户端传送数据
    }
    @RequestMapping(value="collect",method = RequestMethod.POST)
    @ResponseBody
    public String Collect(HttpServletRequest request,HttpServletResponse response,Long product_id) throws IOException{
        UserInfo userInfo=(UserInfo)request.getSession().getAttribute("userInfo");
        Collect collect=new Collect(product_id,userInfo.getAccount());
        collectService.addCollects(collect);//调用关注话题功能
        return "收藏成功!!!";//通过out向客户端传送数据
    }
    @RequestMapping("farmDesc")
    public ModelAndView farmDesc(HttpServletRequest request,Long farm_id){
        ModelAndView mav=new ModelAndView("farmDesc");
        FarmInfo farmInfo=farmInfoService.getFarmInfoById(farm_id);
        farmInfo.setLooks(farmInfo.getLooks()+1);
        farmInfoService.updateFarm(farmInfo);
        List<FarmItem>farmItems=farmItemService.getItems(farm_id);
        List<DescItem> descItems=new ArrayList<>();
        for (FarmItem farmItem:farmItems){
            String status=farmItem.getDescItemByDetailcontentId().getItemStatus();
            if (status!=null&&status.equals("1")){
                descItems.add(farmItem.getDescItemByDetailcontentId());
            }
        }
        System.out.println("某个农场详情"+descItems.size());
        mav.addObject("descItems",descItems);
        mav.addObject("farmInfo",farmInfo);
        return mav;
    }
    @RequestMapping("productDesc")
    public ModelAndView productDesc(HttpServletRequest request,Long product_id){
        ModelAndView mav=new ModelAndView("productDesc");
        ProductInfo productInfo=productInfoService.getProductInfoById(product_id);
        productInfo.setLooks(productInfo.getLooks()+1);
        productInfoService.updateProduct(productInfo);
        List<ProductItem> productItems=productItemService.getItems(product_id);
        List<DescItem> descItems=new ArrayList<>();
        for (ProductItem productItem:productItems){
            String status=productItem.getDescItemByDetailcontentId().getItemStatus();
            if (status!=null&&status.equals("1")){
                descItems.add(productItem.getDescItemByDetailcontentId());
            }
        }
        System.out.println("某个产品详情"+descItems.size());
        mav.addObject("descItems",descItems);
        mav.addObject("productInfo",productInfo);
        return mav;
    }
}
