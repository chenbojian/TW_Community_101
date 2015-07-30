var controllerModule = angular.module("controllerModule", []);

controllerModule.controller("categoryController", function ($scope, $http) {
    $http.get("/admin/api/category/list")
        .success(function (data) {
            $scope.categories = data;
        })
});