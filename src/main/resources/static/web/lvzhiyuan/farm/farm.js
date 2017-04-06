/**
 * Created by 橘 on 2016/12/13.
 */
(function(){
    'use strict';
    var farm_module=angular.module("lvzhiyuan.farm",['ui.router']);//ui.router模块作为主应用模块的依赖模块
    farm_module.factory("farminstance",function(){return {}});
    farm_module.config(["$stateProvider",function($stateProvider){//配置$stateProvider，用来定义路由规则
        $stateProvider.state('lvzhiyuan.farm', {
            url: '/farm',
            title: '农场管理',
            templateUrl: "lvzhiyuan/farm/farmlist.html",
            controller:"farmlistController"
        });
    }]);
    farm_module.controller("farmlistController",["$scope","farmService","$uibModal","farminstance",function($scope,farmService,$uibModal,farminstance){
        $scope.pagetitle="绿之源农场管理";
        $scope.farm_data=[];
        $scope.farm_data.content=[];

        $scope.farmpages=[];

      function loaddata(index,size){
            if(index>0&&index<$scope.farm_data.totalPages){
                farmService.getAllFarms(index,size,function(data){
                    $scope.farm_data=data;
                });
            }else if(index==0){
                farmService.getAllFarms(index,size,function(data){
                    $scope.farm_data=data;
                });
            }else{

            }
        }
        loaddata(0,15);
        $scope.loaddata=loaddata;

        //1.添加农场
        $scope.addfarm = showAddFarm;
        function showAddFarm(){
            var modalInstance = $uibModal.open({
                animation: true,
                templateUrl: 'lvzhiyuan/farm/addfarm.html',
                controller: 'addFarmController',
                bindToController: true,
                size: "lg",
                backdrop: false
            });

            console.log("ruotianjia"+modalInstance.result);
           modalInstance.result.then(function (data) {
             /*   console.log("最后一步"+ data);
                console.log("最后一步"+ data.result);
                console.log("最后一步"+ data.data);*/
                //添加保存成功
                if(data.result > 0) {
                    var farm = data.data;
                    console.log("显示新增农场"+farm.farmName);
                    $scope.farm_data.content.push(farm);
                }
            }, function(flag) {
                if(flag.indexOf("back") >= 0) {
                    return false;
                }
            })
        }
        //2.删除农场
        $scope.deleteFarm=function(farmid){
            console.log("farm_id",farmid);
            var promitInstance = $uibModal.open({
                animation: true,
                templateUrl: 'lvzhiyuan/farm/promit.html',
                controller:function($scope,$uibModalInstance){
                    $scope.title="操作确认";
                    $scope.text="确认执行该农场基本信息的删除操作吗？";
                    $scope.cancel=function(){
                        $uibModalInstance.dismiss('cancel');
                    }
                    $scope.save=function(){
                        $uibModalInstance.close("ok");
                    }
                },
                backdrop:true
            });
            console.log("rrr"+promitInstance.result);
            promitInstance.result.then(function(){
                farmService.deleteFarm(farmid,function(data){
                    if(data.result > 0) {
                        for(var i=0;i<$scope.farm_data.content.length;i++) {
                            if($scope.farm_data.content[i].id == farmid) {
                                //刷新农场页面
                                $scope.farm_data.content.splice(i,1);
                                break;
                            }
                        }
                    }
                });
            });
        }
        //3.更新农场信息
        $scope.updateFarm = showUpdateFarm;
        function showUpdateFarm(updformfarm) {
            console.log("ididi"+updformfarm.id);
            farminstance.modify = updformfarm;
            var modalInstance = $uibModal.open({
                animation: true,
                templateUrl: 'lvzhiyuan/farm/addfarm.html',
                controller: 'modifyFarmController',
                bindToController: true,
                size: "lg",
                backdrop: false
            });
            console.log("genxin"+ data);
            console.log("genxin"+ data.result);
            console.log("genxin"+ data.data);
            modalInstance.result.then(function(data) {
                if(data.result >0) {
                }
            }, function(flag) {
                if(flag.indexOf("back") >= 0) {
                    return false;
                }
            });

        }
        //4.全选功能
        $scope.selectall = function selectall(){
            for(var i=0; i<$scope.farm_data.content.length; i++) {
                if($scope.$isselectall) {
                    $scope.farm_data.content[i].$isselected = true;
                } else {
                    $scope.farm_data.content[i].$isselected = false;
                }
            }
        }
        //5.批量删除功能：先实现获取到选中的几项
        $scope.getSelects = function() {
            var selects = [];
            for(var i=0; i<$scope.farm_data.content.length; i++) {
                if($scope.farm_data.content[i].$isselected) {
                    selects.push($scope.farm_data.content[i]);
                }
            }
            return selects;
        }
        //然后进行批量删除
        $scope.deletefarms = deleteFarms;
        function deleteFarms() {
            if($scope.getSelects().length == 0) {
                return;
            }
            var promitInstance = $uibModal.open({
                animation: true,
                templateUrl: 'lvzhiyuan/farm/promit.html',
                controller:function($scope,$uibModalInstance){
                    $scope.title="操作确认";
                    $scope.text="确认对所选农场执行删除操作吗？";
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
                for(var i=0; i<$scope.farm_data.content.length; i++) {
                    if($scope.farm_data.content[i].$isselected) {
                        farmService.deleteFarm($scope.farm_data.content[i].id,function(data,farmid){
                            if(data.result > 0) {
                                bjs.push(farmid);
                                for(var k=0; k<$scope.farm_data.content.length; k++) {
                                    for(var j=0; j<bjs.length; j++) {
                                        if($scope.farm_data.content[k].id == bjs[j]) {
                                            $scope.farm_data.content.splice(k,1);
                                        }
                                    }
                                }
                            }
                        });
                    }
                }
            });
        }

        //6.查看某农场的详情
        $scope.tofarmItem=function(farm){
            farminstance.currentFarm=farm;
            var farmItemModal = $uibModal.open({
                animation: true,
                templateUrl: 'lvzhiyuan/farm/farmItem.html',
                controller:'farmItemController',
                backdrop:true
            });
        }
    }]);

   farm_module.controller("addFarmController",["$scope","farmService","$uibModalInstance","$rootScope",function($scope,farmService,$uibModalInstance,$rootScope){
        $scope.ModTitle = "添加农场";
        $scope.save = function save(farm) {
            console.log("ha"+$scope.form.farmName)
            $scope.loading = true;
            farmService.addFarm({
                farmName:$scope.form.farmName
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
            /*farmService.addFarm({
                farmName:$scope.form.farmName
            },function (result) {
                console.log(result);
            });*/

        }
        $scope.cancel = function cancel(flag){
            $uibModalInstance.dismiss('cancel');
        }

    }]);

   farm_module.controller("modifyFarmController",["$scope","farmService","$uibModalInstance","farminstance",function($scope,farmService,$uibModalInstance,farminstance){
        $scope.ModTitle = "修改农场";
        $scope.form = farminstance.modify;
        $scope.save = function save(farm) {
            console.log("gggg"+$scope.form.id);
            console.log("kk"+$scope.form.farmName);
            $scope.loading = true;
            farmService.updateFarm({
                farmid:$scope.form.id,
                farmName:$scope.form.farmName
            },function(data) {
                $scope.loading=false;
                console.log(data);
                if(data.result>0) {
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

    farm_module.service("farmService",["$http",function($http){
        //获得全部卡种
        this.getAllFarms=function(index,size,callback){
            $http({
                url:'/farmctrl/'+index+"-"+size,
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
        //1.添加农场
        this.addFarm = function(farm,callback) {
            console.log("农场名称"+farm.farmName);
            $http({
                url:'/farmctrl/addFarm',
                method:'POST',
                data:farm
            }).then(function(response) {
                console.log(response);
                callback(response.data);
            },function (response) {
                console.log("ghjuk"+response);
            });
        }
        //2.删除农场
        this.deleteFarm = function(farmid,callback) {
            $http({
                url:'/farmctrl/deleteFarm/'+farmid,
                method:'DELETE'
            }).then(function(response) {
                callback(response.data,farmid);
            });
        }
        //3.更新农场信息
        this.updateFarm = function(farm,callback) {
            console.log(farm.farmid+farm.farmName);
            $http({
                url:'/farmctrl/updateFarm',
                method:'POST',
                data:farm
            }).then(function(response){
                console.log(response.data);
                callback(response.data);
            });
        }


    }]);
})();