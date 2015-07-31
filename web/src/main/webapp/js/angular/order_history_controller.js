var App = angular.module('App', ['ngCookies']);

App.controller('OrderHistoryController', function($scope, $http, $cookies) {

    $scope.webapi_order_history = $env.contextPath + "/api/order/history";

    $scope.message = '';
    $scope.order_list = null;

    var get_order_list = function() {
        $http.get($scope.webapi_order_history).success(function(data) {
            $scope.order_list = data;
        });
    };
    get_order_list();

    $scope.getOrderGoodsDetail = $functions.getOrderGoodsDetail;

    $scope.launchOrderDetails = function() {
        $cookies.put('orderId', this.order.orderId.toString(), {'path': $env.contextPath + '/'});
        window.location.assign($env.contextPath + '/customer/order-details/');
    }
});
