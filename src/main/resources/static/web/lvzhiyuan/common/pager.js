/**
 * Created by 橘 on 2017/1/10.
 */
(function(){
    'use strict';
    var pager=angular.module("app.pager",[]);
    pager.directive("pager",[function(){
        return {
            restrict: 'EA', //E = element, A = attribute, C = class, M = comment
            scope: {
                pagerData:"=",
                loaddata:'&loaddata'
            },
            templateUrl: 'lvzhiyuan/common/pager.html',
            link: function (scope, element, attributes) {

            }
        }
    }]);

})();
