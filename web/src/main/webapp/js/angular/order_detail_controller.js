var App=angular.module('App',['ngCookies']);
App.controller('OrderDetailController',function($scope,$http,$cookies){
    $scope.webapi_order_detail="/web/api/order/detail";
    $scope.order_id=null;
    $scope.item=null;
    $scope.order_status=null;

    var get_order_id=function(){
        $scope.order_id=parseInt($cookies.get("orderId"),10);
    };
    get_order_id();

    var get_order_detail=function(){
        $http.get($scope.webapi_order_detail+"?orderId="+$scope.order_id).success(function(data){
            $scope.item=data;
            if(data["status"]=="new"){
                $scope.order_status="订单待处理...";
            }else if(data["status"]=="dispatching"){
                $scope.order_status="配送中..."
            }else if(data["status"]=="completed"){
                $scope.order_status="订单已完成..."
            }
        });
    };

    get_order_detail();


});