/**
 * Created by chenjian on 7/21/15.
 */
var App = angular.module("App",[]);

App.controller("orderManagerController", function($scope, $http){

    $scope.newOrdersUrl = "/web/api/order/newOrders";
    $scope.dispatchingOrdersUrl = "/web/api/order/dispatchingOrders";
    $scope.completedOrdersUrl = "/web/api/order/completedOrders";

    $scope.dispatchOrderUrl = "/web/api/order/dispatchOrder";
    $scope.completeOrderUrl = "/web/api/order/completeOrder";

    $scope.newOrders = [];
    $scope.dispatchingOrders = [];
    $scope.completedOrders = [];

    var getNewOrders = function() {
        $http.get($scope.newOrdersUrl).success(function(data, status, headers, config){
            $scope.newOrders = data;
        });
    };

    var getDispatchingOrders = function() {
        $http.get($scope.dispatchingOrdersUrl).success(function(data, status, headers, config){
            $scope.dispatchingOrders = data;
        });
    };

    var getCompletedOrders = function(){
        $http.get($scope.completedOrdersUrl).success(function(data, status, headers, config){
            $scope.completedOrders = data;
        });
    };

    getCompletedOrders();
    getDispatchingOrders();
    getNewOrders();

    $scope.dispatchOrder = function(index){
        var orderId = $scope.newOrders[index].id;
        $http.get($scope.dispatchOrderUrl+"?orderId="+orderId).success(function(data, status, headers, config){
            $scope.newOrders.splice(index, 1);
            getDispatchingOrders();
        });
    };

    $scope.completeOrder = function(index){
        var orderId = $scope.dispatchingOrders[index].id;
        $http.get($scope.completeOrderUrl+"?orderId="+orderId).success(function(data, status, headers, config){
            $scope.dispatchingOrders.splice(index, 1);
            getCompletedOrders();
        });
    };

    setInterval(getNewOrders, 5000);
});
