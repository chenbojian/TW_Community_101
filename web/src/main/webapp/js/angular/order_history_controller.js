var App = angular.module('App', ['ngCookies']);

App.controller('OrderHistoryController', function($scope, $http, $cookies) {
    $scope.webapi_order_history = "/web/api/customer/orders";

    $scope.message = '';
    $scope.order_list = null;

    var get_order_list = function() {
        $http.get($scope.webapi_order_history).success(function(data) {
            $scope.order_list = data;
        });
    }
    get_order_list();

});
