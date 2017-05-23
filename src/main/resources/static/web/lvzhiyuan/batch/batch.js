 /**
 * Created by Wang on 2017/1/3.
 */
(function() {
    'use strict';
    var batch_module = angular.module("lvzhiyuan.batch", ['ui.router']);
    batch_module.factory("batchinstance", function () {
        return {}
    });
    batch_module.config(["$stateProvider", function ($stateProvider) {
        $stateProvider.state('lvzhiyuan.batch', {
            url: '/batch',
            title: '批次管理',
            templateUrl: "lvzhiyuan/batch/batchlist.html",
            controller: "batchlistController"
        });
    }]);

    batch_module.controller("batchlistController",["$scope","batchService","$uibModal","batchinstance",function($scope,batchService,$uibModal,batchinstance){
        $scope.pagetitle="绿之源批次管理";
        $scope.batch_data=[];
        $scope.batchpages=[];
        
        for(var i=0;i<$scope.batch_data.totalPages;i++){
            $scope.pages.push(i);
        }
        $scope.pageindex=$scope.batch_data.number;
        function loaddata(index,size){
            if(index==0){
                batchService.getAllbatches(index,size,function(data){
                    $scope.batch_data=data;
                });
            }else if(index>0&&index<$scope.batch_data.totalPages){
                batchService.getAllbatches(index,size,function(data){
                    $scope.batch_data=data;
                });
            }

        }
        loaddata(0,15);
        $scope.loaddata=loaddata;

        //添加批次
        $scope.addBatch = showAddBatch;
        function showAddBatch(){
            var modalInstance = $uibModal.open({
                animation: true,
                templateUrl: 'lvzhiyuan/batch/addBatch.html',
                controller: 'addBatchController',
                bindToController: true,
                size: "lg",
                backdrop: false
            });

            modalInstance.result.then(function (data) {
                //添加保存成功
                if(data.result > 0) {
                    loaddata(0,15);
                } else {

                }
            }, function(flag) {
                if(flag.indexOf("back") >= 0) {
                    return false;
                }
            })
        }

        //删除批次
        $scope.deleteBatch = function(batchid){
            console.log("batch_id",batchid);
            var promitInstance = $uibModal.open({
                animation: true,
                templateUrl: 'lvzhiyuan/batch/promit.html',
                controller:function($scope,$uibModalInstance){
                    $scope.title="操作确认";
                    $scope.text="确认删除该批次吗？";
                    $scope.cancel=function(){
                        $uibModalInstance.dismiss('cancel');
                    }
                    $scope.save=function(){
                        $uibModalInstance.close("ok");
                    }
                },
                backdrop:true
            });

            promitInstance.result.then(function(){
                batchService.deleteBatch(batchid,function(data){
                    if(data.result > 0) {
                        for(var i=0;i<$scope.batch_data.content.length;i++) {
                            if($scope.batch_data.content[i].id == batchid) {
                                //刷新批次页面
                                $scope.batch_data.content.splice(i,1);
                                break;
                            }
                        }
                    }
                });
            });
        }

        //更新批次
        $scope.updateBatch = showUpdBatch;
        function showUpdBatch(batch) {
            batchinstance.modify = batch;
            var modalInstance = $uibModal.open({
                animation: true,
                templateUrl: 'lvzhiyuan/batch/updateBatch.html',
                controller: 'modifyBatchController',
                bindToController: true,
                size: "lg",
                backdrop: false
            });

            modalInstance.result.then(function(data) {
                if(data.result >0) {
                    loaddata(0,15);
                } else {

                }
            }, function(flag) {
                if(flag.indexOf("back") >= 0) {
                    return false;
                }
            });

        }

        //全选
        $scope.selectall = function selectall(){
            for(var i=0; i<$scope.batch_data.content.length; i++) {
                if($scope.$isselectall) {
                    $scope.batch_data.content[i].$isselected = true;
                } else {
                    $scope.batch_data.content[i].$isselected = false;
                }
            }
        }

        $scope.getSelects = function() {
            var selects = [];
            for(var i=0; i<$scope.batch_data.content.length; i++) {
                if($scope.batch_data.content[i].$isselected) {
                    selects.push($scope.batch_data.content[i]);
                }
            }
            return selects;
        }

        //批量删除批次
        $scope.deleteBatchs = deleteBatchs;
        function deleteBatchs() {
            if($scope.getSelects().length == 0) {
                return;
            }
            var promitInstance = $uibModal.open({
                animation: true,
                templateUrl: 'lvzhiyuan/batch/promit.html',
                controller: function($scope,$uibModalInstance){
                    $scope.title="操作确认";
                    $scope.text="确认删除该批次吗？";
                    $scope.cancel=function(){
                        $uibModalInstance.dismiss('cancel');
                    }
                    $scope.save=function(){
                        $uibModalInstance.close("ok");
                    }
                },
                backdrop:true
            });

            promitInstance.result.then(function(){
                var bjs = [];
                for(var i=0; i<$scope.batch_data.content.length; i++) {
                    if($scope.batch_data.content[i].$isselected) {
                        batchService.deleteBatch($scope.batch_data.content[i].id,function(data,batchid){
                            if(data.result > 0) {
                                bjs.push(batchid);
                                for(var k=0; k<$scope.batch_data.content.length; k++) {
                                    for(var j=0; j<bjs.length; j++) {
                                        if($scope.batch_data.content[k].id == bjs[j]) {
                                            $scope.batch_data.content.splice(k,1);
                                        }
                                    }
                                }
                            }
                        });
                    }
                }
            });
        }

        //批次详情
        $scope.toBatchItem=function(batch){
            batchinstance.currentBatch = batch;
            var batchItemModal = $uibModal.open({
                animation:false,
                templateUrl: 'lvzhiyuan/batch/batchItem.html',
                controller:'batchItemController',
                backdrop:true
            });
        }

        $scope.searchBatch = function searchBatch(searchKey) {
            batchService.getSearchBatch(searchKey,function(data) {
                if(data == "" || data == null) {

                } else {
                    $scope.batch_data = data;
                }
            });
        }
    }]);

   batch_module.controller("addBatchController",["$scope","productService","batchService","$uibModalInstance","$rootScope",function($scope,productService,batchService,$uibModalInstance,$rootScope){
        $scope.pagetitle = "添加批次";
        $scope.batchStatus=["预售","筹备","采摘","运输"];
        $scope.form={};
        productService.getAllProductsWithoutPage(function(data){
           $scope.products=data.data;
       });
       $scope.$watch("form.batch",function(newvalue,oldvalue){
           if(newvalue>0){
               for(var i=0;i<$scope.batchs.length;i++){
                   if($scope.batchs[i].id==newvalue){
                       $scope.form.selectbatch=$scope.batchs[i];
                       $scope.form.batchString=$scope.batchs[i].batchFlag+"-"
                       break;
                   }
               }
           }
       },true);
        $scope.save = function save(batch) {
            console.log(""+$scope.form.productid)
            $scope.loading = true;
            batchService.addBatch({
                productid:$scope.form.productid,
                batchString:$scope.form.batchString,
                batchName:$scope.form.batchName,
                batchNoStart:$scope.form.batchNoStart,
                batchNoEnd:$scope.form.batchNoEnd,
                batchStatus:$scope.form.batchStatus
            },function(data) {
                console.log(data);
                console.log(data.data);
                console.log(data.result);
                $scope.loading = false;
                if(data.result > 0) {
                    $uibModalInstance.close(data);
                } else {
                    $scope.error = {
                        haserror: true,
                        errormsg: "添加失败，您可以再试一次！"
                    }
                }
            });
        }
        $scope.cancel = function cancel(flag){
            $uibModalInstance.dismiss('cancel');
        }

    }]);

   batch_module.controller("modifyBatchController",["$scope","batchService","$uibModalInstance","batchinstance",function($scope,batchService,$uibModalInstance,batchinstance){
        $scope.pagetitle = "修改批次";
        $scope.batchStatus=["预售","筹备","采摘","运输"];
        $scope.form = batchinstance.modify;
        $scope.save = function save(batch) {
            console.log("批次的id"+$scope.form.id);
            $scope.loading = true;
            batchService.updateBatch($scope.form,function(data) {
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

        $scope.cancel=function cancel(flag){
            $uibModalInstance.dismiss('cancel');
        }

   }]);

    batch_module.controller("batchItemController",["$scope","batchinstance","batchService","$uibModalInstance",function($scope,batchinstance,batchService,$uibModalInstance){
        $scope.batch=batchinstance.currentBatch;
        $scope.title=$scope.batch.batchName+"-信息维护";
        $scope.batchid=batchinstance.currentBatch.id;

        $scope.$watch("additem",function(newvalue,oldvalue){
            if(newvalue!=null){
                batchService.addBatchItem(newvalue.id, $scope.batchid, function (data) {
                    loaditems($scope.batchid);
                });
            }
        });
        $scope.cancel = function cancel(flag){
            $uibModalInstance.dismiss('cancel');
        }

        loaditems($scope.batchid);
        function loaditems(batchid){
            //加载该批次的详情
            batchService.loadItems(batchid,function(items){
                console.log("batch load items",items);
                $scope.items=[];
                for(var i=0;i<items.length;i++){
                    $scope.items.push(items[i].detailContentId);
                }
            });
        }
    }]);
    
   batch_module.service("batchService",["$http",function($http){
        this.getAllbatches=function(index,size,callback){
            $http({
                url:'/batch/'+index+"-"+size,
                method:'GET',
                data:{
                    index:index,
                    size:size
                }
            }).then(function(data){
                console.log(data);
                callback(data.data);
            });
        }

       this.addBatch = function(batch,callback) {
            $http({
                url:'/batch/addBatch',
                method:'POST',
                data:batch
            }).then(function(response) {
                callback(response.data);
            },function(response) {

            });
        }

       this.deleteBatch = function(batchid,callback) {
            $http({
                url:'/batch/deleteBatch/'+batchid,
                method:'DELETE'
            }).then(function(response) {
                callback(response.data,batchid);
            });
       }

       this.updateBatch = function(batch,callback) {
            $http({
                url:'/batch/updateBatch',
                method:'POST',
                data:batch
            }).then(function(response){
                callback(response.data);
            },function(response){

            });
       }

       this.loadItems=function(batchid,callback){
           $http({
               url:"/batch/items",
               method:'POST',
               data:{
                   batchid:batchid
               }
           }).then(function(data){
               if(callback){
                   callback(data.data);
               }
           });
       }
       this.addBatchItem=function(descid,batchid,callback){
           $http({
               url:"/batch/items/add",
               method:'POST',
               data:{
                   descid:descid,
                   batchid:batchid
               }
           }).then(function(data){
               if(callback){
                   callback(data.data);
               }
           });
       }

       this.getSearchBatch = function(searchKey,callback) {
            if(searchKey == "" || searchKey == null) {
                var url = '/batch/batchs';
            }
            var url = '/batch/batchs/'+searchKey;
            $http({
                url:url,
                method:'GET'
            }).then(function(response){
                callback(response.data);
            });
        }
   }]);
})();