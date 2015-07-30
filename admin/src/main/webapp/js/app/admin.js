var adminApp = angular.module("adminApp",[
    "ngRoute",
    "controllerModule"
]);

adminApp.config(function ($routeProvider) {
    $routeProvider
        .when('/category/manage', {
            controller: 'categoryController',
            templateUrl: 'manageCategory'
        })
        .otherwise("/category/manage");
});