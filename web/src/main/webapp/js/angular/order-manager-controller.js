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

    $scope._title = document.title;
    $scope.sound = document.getElementById('sound');

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

    $scope.toFixed = function(price){  //保留2位小数
        return price.toFixed(2);
    }

    //  打开页面初始化订单列表
    getCompletedOrders();
    getDispatchingOrders();
    getNewOrders();

    $scope.dispatchOrder = function(index){
        var orderId = $scope.newOrders[index].id;
        $http.get($scope.dispatchOrderUrl+"?orderId="+orderId).success(function(data, status, headers, config){
            $scope.newOrders.splice(index, 1);

            getDispatchingOrders();  //刷新派送中的订单

        });
    };

    $scope.completeOrder = function(index){
        var orderId = $scope.dispatchingOrders[index].id;
        $http.get($scope.completeOrderUrl+"?orderId="+orderId).success(function(data, status, headers, config){
            $scope.dispatchingOrders.splice(index, 1);

            getCompletedOrders();    //刷新已完成的订单
        });
    };

    var show = function() { //有新消息时在title处闪烁提示
        var step=0;

        var timer = setInterval(function() {
            step++;
            if (step==3) {step=1};
            if (step==1) {document.title='【　　　　　】'+$scope._title};
            if (step==2) {document.title='【您有新订单】'+$scope._title};
        }, 500);

        setTimeout(function(){
            clearInterval(timer);
            document.title = $scope._title;
        }, 10000);
    };

    $scope.$watch("newOrders", function(newValue, oldValue, scope){  //监测新订单列表，有新订单的话闪烁标题并发出提示音
        if(newValue.length>oldValue.length){
            show();
            $scope.sound.play();
        }
    },true);

    setInterval(getNewOrders, 5000);
});
