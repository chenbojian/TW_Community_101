var App=angular.module('App',[ngCookies]);
App.controller('OrderDetailController',function($scope,$http,$cookies){
    $scope.webapi_order_detail="web/ordersStatus/detail";
    $scope.order_id=null;
    $scope.item=null;

    var get_order_id=function(){
        //$cookies.put("orderId", 1);
        //$scope.order_id=Int.parse(cookies.get("orderId"));
        //console.log($scope.order_id);
        $scope.order_id=1;
    };
    get_order_id();

    var get_order_detail=function(){
        $http.get($scope.webapi_order_detail+"?id="+$scope.order_id).success(function(data){
            $scope.item=data;
        });
    };
    get_order_detail();

});