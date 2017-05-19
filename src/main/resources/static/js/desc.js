/**
 * Created by master on 2017/5/14.
 */
var app = angular.module('descApp', []);
app.controller('descController', function($scope) {
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
});
app.controller("batchDescModalController",["$scope","batchDescinstance","getBatchService","$uibModalInstance",function ($scope,batchDescinstance,getBatchService,$uibModalInstance) {
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
app.service("getBatchService",["$http",function ($http) {
    /*//批次基本信息
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
     }*/
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
    /*this.getProductDesc=function (batchinfo,callback) {
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
     }*/
}]);
