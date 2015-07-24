var App = angular.module('App', ['ngCookies']);

App.controller('OrderHistoryController', function($scope, $http, $cookies) {
    $scope.is_fake = true;
    $scope.webapi_order_history = "/web/api/customer/orders";

    $scope.message = '';
    $scope.phone = '11111111111';
    $scope.order_list = null;

    var get_order_list = function() {
        $http.get($scope.webapi_order_history + '?phone=' + $scope.phone).success(function(data) {
            $scope.order_list = data;
        });
    }
    get_order_list();

});
