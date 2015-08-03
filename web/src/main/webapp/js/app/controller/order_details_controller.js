angular.module('webApp')
    .controller('OrderDetailsController',function($scope, $http, $cookies){

    $scope.webapi_order_detail=env.contextPath + "/api/order/detail";
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
            $scope.order_status = data.status;
        });
    };

    get_order_detail();

    $scope.getOrderGoodsDetail = functions.getOrderGoodsDetail;

});