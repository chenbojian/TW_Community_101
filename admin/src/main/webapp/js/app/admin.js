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
            .otherwise("/category/manage");
    });