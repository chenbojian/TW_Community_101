var App = angular.module('App', ['ngCookies']);

App.controller('DetailsController', function($scope, $http, $cookies) {

    $scope.is_fake = true;

    $scope.item_id = null;
    $scope.item = null;

    var get_id = function() {
        if ($scope.is_fake) {
            $scope.item_id = 1;
        }
        else {
            $scope.item_id = Int.Parser($cookies.get("goods_id"));
        }
    };
    get_id();

    var get_details = function() {
        if ($scope.is_fake) {
            $scope.item = {"id":$scope.item_id,"name":"fake goods","price":2143,"pic":"//baidu.com/","description":"very good"};
        }
        else {
            $http.get("/web/api/customer/goods/details" + "?id=" + $scope.item_id).success(function(data, status, headers, config) {
                $scope.item = data;
            });
        }
    };
    get_details();

    var add_to_cart = function() {
        alert("not added");
    };
});
