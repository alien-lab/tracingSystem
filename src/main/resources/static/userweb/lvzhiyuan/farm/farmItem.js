/**
 * Created by perry on 2016/12/19.
 */
(function(){
    'use strict';
    var farmItem_module=angular.module("lvzhiyuan.farmItem",['ui.router']);
    farmItem_module.controller("farmItemController",["$scope","farmItemService","farminstance","$uibModalInstance",
        function($scope,farmItemService,farminstance,$uibModalInstance){
            $scope.title="农场信息维护";
            //加载当前打开的农场id
           $scope.farmid = farminstance.currentFarm.id;
            // $scope.additem={};
             $scope.$watch("additem",function(newvalue,oldvalue){//$watch是一个scope函数，用于监听模型变化，当你的模型部分发生变化时它会通知你
                if(newvalue){
                    farmItemService.addFarmItem(newvalue.id, $scope.farmid, function (data) {
                        console.log("开始传参"+$scope.farmid);
                        loaditems($scope.farmid);
                    });
                }
            });
            $scope.cancel = function cancel(flag){
                $uibModalInstance.dismiss('cancel');
            }

            loaditems($scope.farmid);
            function loaditems(farmid){
                console.log("传参a"+$scope.farmid);
                //加载该农场的详情
                farmItemService.loadItems(farmid,function(items){
                    console.log(items);
                    $scope.items=[];
                    for(var i=0;i<items.length;i++){
                        $scope.items.push(items[i].descItemByDetailcontentId);
                    }
                    console.log("jieguo"+$scope.items);
                });
            }



        }]);

    farmItem_module.service("farmItemService",function farmItemService($http){

        this.loadItems=function(farmid,callback){
            console.log("传参b"+farmid);
            $http({
                url:'/farmctrl/items/'+farmid,
                method:'GET'
            }).then(function(response){
                callback(response.data);
                if(response){
                    callback(response.data);
                }
            });
        }
        this.addFarmItem=function(itemid,farmid,callback){
            console.log("lallal"+itemid+farmid)
            $http({
                url:'/farmctrl/items',
                method:'POST',
                data:{
                    itemid:itemid,
                    farmid:farmid
                }
            }).then(function(response){
                console.log(response);
                if(response){
                    callback(response.data);
                }
            });
        }
    });


})();