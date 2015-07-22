var App=angular.module('App',['ngCookies']);
App.controller('OrderStatusController',function($scope,$http,$cookies){
    $scope.new_orders_list="/web/ordersStatus/newOrdersList";
    $scope.new_list=null;
    $scope.dispatching_orders_list="/web/ordersStatus/dispatchingOrdersList";
    $scope.dispatching_list=null;
    $scope.completed_orders_list="/web/ordersStatus/completedOrdersList";
    $scope.completed_list=null;


    var get_new_orders_list=function(){
        $http.get($scope.new_orders_list).success(function(data){
            $scope.new_list=data;
        });
    };
    var get_dispatching_orders_list=function(){
        $http.get($scope.dispatching_orders_list).success(function(data){
            $scope.dispatching_list=data;
        });
    };
    var get_completed_orders_list=function(){
        $http.get($scope.completed_orders_list).success(function(data){
            $scope.completed_list=data;
        });
    };

    get_new_orders_list();
    get_dispatching_orders_list();
    get_completed_orders_list();





});