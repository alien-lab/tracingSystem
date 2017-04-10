/**
 * Created by 橘 on 2016/12/13.
 */
(function(){
    'use strict';
    var product_module=angular.module("lvzhiyuan.product",['ui.router','lvzhiyuan.farm']);
    product_module.factory("productinstance",function(){return {}});
    product_module.config(["$stateProvider",function ($stateProvider) {
        $stateProvider.state('lvzhiyuan.product',{
            url:'/product',
            title:'产品管理',
            templateUrl:"lvzhiyuan/product/productlist.html",
            controller:"productlistController"
        });
    }]);
    product_module.controller("productlistController",["$rootScope","$http","$scope","productService","$uibModal","productinstance",function($rootScope,$http,$scope,productService,$uibModal,productinstance) {
        $scope.pagetitle="绿之源产品管理";
        $scope.product_data=[];

        function loaddata(index,size){
            if(index>0&&index<$scope.product_data.totalPages){
                productService.getAllProducts(index,size,function (data) {
                    $scope.product_data=data;
                })
            }else if(index==0){
                productService.getAllProducts(index,size,function (data) {
                    $scope.product_data=data;
                })
            }
        }
        loaddata(0,15);
        $scope.loaddata=loaddata;

        $scope.addProduct=showAddProduct;
        //添加弹出框
       function showAddProduct(){
           var modalInstance = $uibModal.open({
               animation: true,
               templateUrl: 'lvzhiyuan/product/addProduct.html',
               controller: 'addProductController',
               bindToController:true,
               size: "lg",
               backdrop:false
           });
           modalInstance.result.then(function (data) {
               //添加保存成功
               if(data.result > 0) {
                   var product = data.data;
                   console.log("显示新增农场"+product.productName);
                   $scope.product_data.content.push(product);
               }
           }, function(flag) {
               if(flag.indexOf("back") >= 0) {
                   return false;
               }
           })
        }
        $scope.deleteProduct=function deleteProduct(id){
            console.log("id"+id);
            var promitInstance=$uibModal.open({
                animation:true,
                templateUrl:'lvzhiyuan/product/promit.html',
                controller:function ($scope,$uibModalInstance) {
                    $scope.title="操作确认";
                    $scope.text="确认删除该产品吗？";
                    $scope.cancel=function () {
                        $uibModalInstance.dismiss('cancel');
                    }
                    $scope.save=function () {
                        $uibModalInstance.close('ok');
                    }
                },
                backdrop:true
            });
            promitInstance.result.then(function () {
                productService.deleteProduct(id,function (data) {
                    if(data.result>0){
                         for(var i=0;i<$scope.product_data.content.length;i++){
                             if($scope.product_data.content[i].id==id){
                                 //刷新产品页面
                                 $scope.product_data.content.splice(i,1);
                                 break;
                             }
                         }
                    }
                });
            })
        }
        $scope.updateProduct=showUpdateProduct;
        //修改产品信息的弹出框
        function showUpdateProduct(product) {
            console.log(product.id);
            productinstance.modify=product;
            var modalInstance=$uibModal.open({
                animation: true,
                templateUrl: 'lvzhiyuan/product/updateProduct.html',
                controller: 'modifyProductController',
                bindToController:true,
                size: "lg",
                backdrop:false

            });
            modalInstance.result.then(function(data) {
                if(data.result >0) {
                }
            }, function(flag) {
                if(flag.indexOf("back") >= 0) {
                    return false;
                }
            });
        }
        $scope.selectall=function () {
            console.log($scope,$scope.$isselectall);

            for(var i=0;i<$scope.product_data.content.length;i++){
                if($scope.$isselectall){
                        $scope.product_data.content[i].$isselected=true;
                }else
                {
                    $scope.product_data.content[i].$isselected = false;
                }
            }
        }
        $scope.deleteProducts=deleteProducts;
        $scope.getSelects=function () {
            var selects=[];
            for(var i=0;i<$scope.product_data.content.length;i++){
                if($scope.product_data.content[i].$isselected){
                    selects.push($scope.product_data.content[i]);
                }
            }
            return selects;
        }
        function deleteProducts() {
            console.log($scope.getSelects().length+"lalal");
            if($scope.getSelects().length==0){
                return;
            }
            var promitInstance=$uibModal.open({
                animation:true,
                templateUrl:'lvzhiyuan/product/promit.html',
                controller:function ($scope,$uibModalInstance) {
                    $scope.title="操作确认";
                    $scope.text="确认对所选产品执行删除操作吗?";
                    $scope.cancel=function () {
                        $uibModalInstance.dismiss('cancel');
                    }
                    $scope.save=function () {
                        $uibModalInstance.close('ok');
                    }
                },
                backdrop:true
            });
            promitInstance.result.then(function () {
                var deletes=[];
                for(var i=0;i<$scope.product_data.content.length;i++){
                    if($scope.product_data.content[i].$isselected) {
                        productService.deleteProduct($scope.product_data.content[i].id,function(data,id){
                            if(data.result > 0) {
                                deletes.push(id);
                                for(var k=0; k<$scope.product_data.content.length; k++) {
                                    for(var j=0; j<deletes.length; j++) {
                                        if($scope.product_data.content[k].id == deletes[j]) {
                                            $scope.product_data.content.splice(k,1);
                                        }
                                    }
                                }
                            }
                        });
                    }
                }
            })
        }

        //产品详情
        $scope.toProductItem=function(product){
            productinstance.currentProduct=product;
            var productItemModal = $uibModal.open({
                animation: false,
                templateUrl: 'lvzhiyuan/product/productItem.html',
                controller:'productItemController',
                backdrop:true
            });
        }

        $scope.searchProduct=function searchProduct(searchKey){
               productService.getSearchProduct(searchKey,function (data) {

                   if(data==""||data==null){

                   }else{
                       var resp={"content":data,"totalPages":1};
                       $scope.product_data=[];
                       $scope.product_data=resp;
                       console.log($scope.product_data);
                   }
               })
        }
    }]);
    product_module.controller("addProductController",["$scope","productService","farmService","$uibModalInstance","productinstance","$rootScope",function($scope,productService,farmService,$uibModalInstance,productinstance,$rootScope){
        $scope.productStatus=["预售","筹备","采摘","运输"];
        $scope.pagetitle="添加产品";
        //添加产品默认值
        $scope.form={}
        farmService.getAllFarmsWithoutPage(function(data){
            $scope.farm_data=data.data;
        });
        $scope.save = function save(product) {
            $scope.loading = true;
            productService.addProduct({
                productFlag: $scope.form.productFlag,
                productName:$scope.form.productName,
                productDesc:$scope.form.productDesc,
                productPrice:$scope.form.productPrice,
                farmid:$scope.form.farmid,
                status:$scope.form.status
            },function (data) {
                $scope.loading=false;
                console.log("wwww"+data.result);
                if(data.result>0){
                    $uibModalInstance.close(data);
                }else{
                    $scope.error = {
                        haserror: true,
                        errormsg: "添加失败，您可以再试一次！"
                    }
                }
            });


        }
      $scope.cancel=function cancel(flag){
          $uibModalInstance.dismiss('cancel');
      }
    }]);



    product_module.controller("modifyProductController",["$scope","farmService","productService","$uibModalInstance","productinstance",function ($scope,farmService,productService,$uibModalInstance,productinstance) {
        $scope.pagetitle="更新产品信息";
        $scope.productStatus=["预售","筹备","采摘","运输"];
        console.log("modifyProductController".$scope);
        $scope.form=productinstance.modify;
        console.log($scope.form);
        $scope.form.advoption=true;
        farmService.getAllFarmsWithoutPage(function(data){
            $scope.farm_data=data.data;
        });
        $scope.save=function save(updateform){
            $scope.loading=true;
            console.log("所传id"+$scope.form.id)
            productService.updateProduct({
                productId:$scope.form.id,
                productFlag: $scope.form.productFlag,
                productName:$scope.form.productName,
                productDesc:$scope.form.productDesc,
                productPrice:$scope.form.productPrice,
                farmid:$scope.form.farmid,
                status:$scope.form.status
            },function (data) {
                // console.log($scope.form);
                // console.log(data);
                $scope.loading = false;
                if(data.result > 0) {
                    $uibModalInstance.close(data);
                } else {
                    $scope.error = {
                        haserror: true,
                        errormsg : "修改失败，您可以再试一次！"
                    }
                }
            });
        }
        $scope.cancel=function cancel(flag) {
            $uibModalInstance.dismiss('cancel');
        }
    }]);


    product_module.controller("productItemController",["$scope","productinstance","productService","$uibModalInstance",function($scope,productinstance,productService,$uibModalInstance){
        $scope.product=productinstance.currentProduct;
        $scope.title=$scope.product.productName+"-信息维护";
        $scope.productid=productinstance.currentProduct.id;

        $scope.$watch("additem",function(newvalue,oldvalue){
            if(newvalue!=null){
                productService.addProductItem(newvalue.id, $scope.productid, function (data) {
                    loaditems($scope.productid);
                });
            }
        });
        $scope.cancel = function cancel(flag){
            $uibModalInstance.dismiss('cancel');
        }

        loaditems($scope.productid);
        function loaditems(productid){
            //加载该产品的详情
            productService.loadItems(productid,function(items){
                console.log("product load items",items);
                $scope.items=[];
                for(var i=0;i<items.length;i++){
                    $scope.items.push(items[i].descItemByDetailcontentId);
                }
            });
        }
    }]);


    product_module.service("productService",["$http",function($http) {
        this.getAllProducts=function (index,size,callback) {
            $http({
                url:'/product/'+index+"-"+size,
                method:'GET',
                data:{
                    index:index,
                    size:size
                }
            }).then(function (data) {
                console.log(data);
                callback(data.data);
            });
        }
        this.getAllProductsWithoutPage=function(callback){
            $http({
                url:'/product/all',
                method:'GET'
            }).then(function (data) {
                console.log(data);
                callback(data.data);
            });
        }

        this.addProduct=function(product,callback){
            $http({
                url:'/product/add',
                method:'POST',
                data:product
            }).then(function(response) {
                console.log(response);
                callback(response.data);
            },function (response) {
                console.log("ghjuk"+response);
            });
        }
        this.deleteProduct=function (id,callback) {
            $http({
                url:'/product/delete/'+id,
                method:'DELETE',
                data:id
            }).then(function (response) {
                console.log("product/delete",response);
                callback(response.data,id);
            })
        }
        this.updateProduct=function (product,callback) {
            $http({
                url:'/product/update',
                method:'POST',
                data:product
            }).then(function (response) {
                console.log(response);
                callback(response.data);
            }),function (response) {

            }
        }
        this.getSearchProduct=function (searchKey,callback) {
            var url="";
            if(searchKey=="" || searchKey==null){
                 url="/product";
                console.log("aaa");
            }else{
                console.log("bbb")
                url="/product/"+searchKey;
            }
            console.log(url);
            $http({
                url:url,
                method:'POST'
            }).then(function (response) {
                callback(response.data);
                console.log(response.data);
            })

        }

        this.loadItems=function(productid,callback){
            console.log("产品id"+productid)
            $http({
                url:"/product/items",
                method:'POST',
                data:{
                    pid:productid
                }
            }).then(function(data){
                if(callback){
                    callback(data.data);
                }
            });
        }
        this.addProductItem=function(descid,productid,callback){
            $http({
                url:"/product/items/add",
                method:'POST',
                data:{
                    descid:descid,
                    pid:productid
                }
            }).then(function(data){
                if(callback){
                    callback(data.data);
                }
            });
        }
    }]);

})();



