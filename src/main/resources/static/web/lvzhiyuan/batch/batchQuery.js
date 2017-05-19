/**
 * Created by Administrator on 2016/12/20.
 */
(function(){
    'use strict';
    var batch_module=angular.module("lvzhiyuan.batchQuery",['ui.router']);
    batch_module.factory("batchDescinstance",function(){return {}});
    batch_module.config(["$stateProvider",function($stateProvider){
        $stateProvider.state('lvzhiyuan.batchQuery', {
            url: '/batchQuery',
            title: '批次管理',
            templateUrl: "lvzhiyuan/batch/batchQuery.html",
            controller:"getbatchController"
        });
    }]);
    batch_module.controller("getbatchController",["$scope","getBatchService","batchDescinstance","$uibModal",function ($scope,getBatchService,batchDescinstance,$uibModal) {
        $scope.pagetitle="绿之源鲜果批次查询";
        $scope.infostates = false;
        //批次基本信息
        $scope.getBatch=function getBatch(batchString){
            var str = batchString;
            var name = str.split("-")[0];
            var batchnumber = str.split("-")[1];
            $scope.ordernumber = str.split("-")[2];
            var batchinfo = name+"-"+batchnumber;//batchinfo 为搜索内容分割后的 例如：LZYLIZI-1
            $scope.batchinfo = batchinfo;
            $scope.infostates = true;
            getBatchService.getBatch(batchinfo,function(data){
                 $scope.batch_data=data;
                 console.log(data);
            });
        }
        //批次信息模态框
        $scope.toBatchinfo=function(batchinfo){
            batchDescinstance.currentBatchInfo=batchinfo;
            var batchinfoModal = $uibModal.open({
                animation:false,
                templateUrl: 'lvzhiyuan/batch/batchInfo.html',
                controller:'batchDescModalController',
                backdrop:true
            });
        }

        //产品信息模态框
        $scope.toProductinfo=function(batchinfo){
            batchDescinstance.currentBatchInfo=batchinfo;
            var ProductinfoModal = $uibModal.open({
                animation:false,
                templateUrl: 'lvzhiyuan/batch/batchProductinfo.html',
                controller:'productDescModalController',
                backdrop:true
            });
        }

        //农场信息模态框
        $scope.toFarminfo=function(batchinfo){
            batchDescinstance.currentBatchInfo=batchinfo;
            var farmItemModal = $uibModal.open({
                animation: false,
                templateUrl: 'lvzhiyuan/batch/batchFarminfo.html',
                controller:'farmDescModalController',
                backdrop:true
            });
        }
    }]);
    batch_module.controller("batchDescModalController",["$scope","batchDescinstance","getBatchService","$uibModalInstance",function ($scope,batchDescinstance,getBatchService,$uibModalInstance) {
        $scope.title="批次详情";
        $scope.batchinfo=batchDescinstance.currentBatchInfo;
        $scope.cancel = function cancel(flag){
            $uibModalInstance.dismiss('cancel');
        }
        loaditems($scope.batchinfo);
        function loaditems(batchinfo){
            //加载该批次的详情
            getBatchService.getBatchDesc(batchinfo,function(items){
                console.log("batch load items",items);
                $scope.items=[];
                for(var i=0;i<items.length;i++){
                    $scope.items.push(items[i]);
                }
                console.log(items);
            });
        }
    }]);

    batch_module.controller("productDescModalController",["$scope","batchDescinstance","getBatchService","$uibModalInstance",function ($scope,batchDescinstance,getBatchService,$uibModalInstance) {
        $scope.title="产品详情";
        $scope.batchinfo=batchDescinstance.currentBatchInfo;
        $scope.cancel = function cancel(flag){
            $uibModalInstance.dismiss('cancel');
        }
        loaditems($scope.batchinfo);
        function loaditems(batchinfo){
            //加载该产品的详情
            getBatchService.getProductDesc(batchinfo,function(items){
                console.log("product load items",items);
                $scope.items=[];
                for(var i=0;i<items.length;i++){
                    $scope.items.push(items[i]);
                }
                console.log(items);
            });
        }
    }]);
    batch_module.controller("farmDescModalController",["$scope","batchDescinstance","getBatchService","$uibModalInstance",function ($scope,batchDescinstance,getBatchService,$uibModalInstance) {
        $scope.title="农场详情";
        $scope.batchinfo=batchDescinstance.currentBatchInfo;
        $scope.cancel = function cancel(flag){
            $uibModalInstance.dismiss('cancel');
        }
        loaditems($scope.batchinfo);
        function loaditems(batchinfo){
            //加载该农场的详情
            getBatchService.getFarmDesc(batchinfo,function(items){
                console.log("farm load items",items);
                $scope.items=[];
                for(var i=0;i<items.length;i++){
                    $scope.items.push(items[i]);
                }
                console.log(items);
            });
        }
    }]);
    batch_module.service("getBatchService",["$http",function ($http) {
        //批次基本信息
        this.getBatch=function (batchinfo,callback) {
            $http({
             url:"/batch/batchInfo/"+batchinfo,
             method:"GET",
             data:{
             batchString:batchinfo
             }
             }).then(function (data) {
             console.log(data.data.data);
             callback(data.data.data);
             });
        }
        //批次详情
        this.getBatchDesc=function (batchinfo,callback) {
             $http({
                 url:"/batch/batchDesc/"+batchinfo,
                 method:"GET",
                 data:{
                     batchString:batchinfo
                 }
             }).then(function (data) {
                 console.log("ahhaah"+data.data.data);
                 callback(data.data.data);
             });
         }
        //批次对应产品详情
        this.getProductDesc=function (batchinfo,callback) {
            $http({
                url:"/batch/productDesc/"+batchinfo,
                method:"GET",
                data:{
                    batchString:batchinfo
                }
            }).then(function (data) {
                console.log(data.data.data);
                console.log("ahhaah"+data.data);
                console.log("ahhaah"+data);
                callback(data.data.data);
            });
        }
        //批次对应农场详情
        this.getFarmDesc=function (batchinfo,callback) {
            $http({
                url:"/batch/farmDesc/"+batchinfo,
                method:"GET",
                data:{
                    batchString:batchinfo
                }
            }).then(function (data) {
                console.log(data.data.data);
                callback(data.data.data);
            });
        }
    }]);
})();
