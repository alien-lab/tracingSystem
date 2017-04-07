/**
 * Created by fyq on 2016/12/20.
 */
(function(){
    'use strict';
    var desc_module=angular.module("lvzhiyuan.desc",[]);
    desc_module.service("descService",['$http',function ($http) {

    }]);

    desc_module.directive("addDesc",["descService","$filter","$http",function(descService,$filter,$http){
        return {
            restrict: 'EA', //E = element, A = attribute, C = class, M = comment
            scope: {
                model: '@', //选择模式
                descid: "=",  //被选中的产品
                item:"="
            },
            templateUrl: 'lvzhiyuan/desc/addDesc.html',
            link: function (scope, element, attr, ctrl) {
                scope.loading=false;//是否显示loading
                scope.image=null;//当前图片
                scope.text="";//当前文本
                scope.link=null;//当前链接
                scope.label=null;//当前标签
                scope.$watch("image",function(newvalue,oldvalue){
                    if(scope.image!=null){
                        scope.type='image';
                    }
                },true);
                scope.type="unknow";
                if(scope.model!=null)scope.type=model;//如果已经指定了类型
                scope.chooseImage=function(){
                    scope.type="image";
                }
                scope.chooseText=function(){
                    scope.type="text";
                }
                scope.save=function(){
                    console.log("ww"+scope.type);
                    if(scope.type=="image"){
                        $http({
                            url:'/desc/addDescImage',
                            method:'POST',
                            data:{
                                type:scope.type,
                                text:scope.text,
                                link:scope.link,
                                label:scope.label,
                                file:(scope.image)?(scope.image.file):null
                            },
                        }).then(function (data, status, headers, config) {
                            console.log("添加详情"+data.data);
                            if(data.data.id>0){
                                scope.descid=data.data.id;
                                scope.item=data.data;
                                scope.cancel();
                            }
                        });
                    }else{
                        $http({
                            url:'/desc/addDescText',
                            method:'POST',
                            data:{
                                type:scope.type,
                                text:scope.text,
                                link:scope.link,
                                label:scope.label
                            },
                        }).then(function (data, status, headers, config) {
                            console.log("添加详情"+data.data);
                            if(data.data.id>0){
                                scope.descid=data.data.id;
                                scope.item=data.data;
                                scope.cancel();
                            }
                        });
                    }

                }
                scope.cancel=function(){
                    scope.text="";
                    scope.link=null;
                    scope.label=null;
                    scope.image=null;
                    scope.type='unknow';
                }

            }
        }
    }]);
    desc_module.directive("descItems",["descService","$filter","$http",function(descService,$filter,$http){
        return {
            restrict: 'EA', //E = element, A = attribute, C = class, M = comment
            scope: {
                items: "="
            },
            templateUrl: 'lvzhiyuan/desc/descItem.html',
            link: function (scope, element, attributes) {
                scope.publish=function(item){
                    if(item.itemStatus!='1'&&item.itemStatus!=1){
                        changestatus(item,1,function(data){
                            item.itemStatus=1;
                        })
                    }
                }
                scope.unpublish=function(item){
                    if(item.itemStatus=='1'||item.itemStatus==1){
                        changestatus(item,0,function(data){
                            item.itemStatus=0;
                        })
                    }
                }
                scope.deleteDesc=function(item){
                    console.log("htghgh"+item.id);
                    $http({
                        url:'/desc/deleteDesc',
                        method:'POST',
                        data:{
                            itemid:item.id
                        }
                    }).then(function(data){
                        if(data&&data.data){
                            for(var i=0;i<scope.items.length;i++){
                                if(scope.items[i].id=item.id){
                                    scope.items.splice(i,1);
                                    break;
                                }
                            }
                        }
                    });
                }

                function changestatus(item,status,callback){
                    $http({
                        url:'/desc/updateDesc',
                        method:'POST',
                        data:{
                            itemid:item.id,
                            status:status
                        }
                    }).then(function(data){
                        if(data&&data.data&&callback){
                            callback(data.data);
                        }
                    });
                }
            }
        }
    }]);

    desc_module.directive("imagepreview",[function(){
        return {
            scope: {
                fileread: "="
            },
            link: function (scope, element, attributes) {
                element.bind("change", function (changeEvent) {
                    var reader = new FileReader();
                    console.log("changeEvent",changeEvent);
                    reader.onload = function (loadEvent) {
                        scope.$apply(function () {
                            scope.fileread = loadEvent.target.result;
                            //scope.fileinfo=changeEvent.target.files[0];
                        });
                    }
                    reader.readAsDataURL(changeEvent.target.files[0]);
                });
            }
        }
    }]);
})();