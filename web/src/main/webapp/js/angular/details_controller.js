var App = angular.module('App', ['ngCookies']);

App.controller('DetailsController', function($scope, $http, $cookies) {

    $scope.is_fake = true;

    $scope.webapi_goods_details = "/web/api/customer/goods/details";
    $scope.item_id = null;
    $scope.item = null;

    var get_id = function() {
        if ($scope.is_fake) {
            $scope.item_id = 1;
        }
        else {
            $scope.item_id = parseInt($cookies.get("goods_id"), 10);
        }
    };
    get_id();

    var get_details = function() {
        $http.get($scope.webapi_goods_details + "?id=" + $scope.item_id).success(function(data, status, headers, config) {
            $scope.item = data;
        });
    };
    get_details();

    var add_to_cart = function() {
        alert("not added");
    };
});
