angular
    .module("adminApp", [
        "ngRoute",
        "ngResource"
    ])
    .config(function ($routeProvider) {
        $routeProvider
            .when('/category/manage', {
                controller: 'categoryController',
                templateUrl: 'manageCategory'
            })
            .when('/order-manager', {
                controller: 'orderManagerController',
                templateUrl: 'order-manager'
            })
            .when('/order-status', {
                controller: 'orderStatusController',
                templateUrl: 'order-status'
            })
            .otherwise("/order-manager");
    });