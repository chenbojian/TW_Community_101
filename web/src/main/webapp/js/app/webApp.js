angular.module("webApp", ["ngRoute", "ngResource", "ngCookies"])
    .config(function ($routeProvider) {
        $routeProvider
            .when('/demonstration', {
            controller: 'IndexController',
            templateUrl: 'demonstration/'
        })
            .when('/shopping-cart', {
                controller: 'CartController',
                templateUrl: 'shopping-cart/'
            })
            .when('/order-details', {
                controller: 'OrderDetailsController',
                templateUrl: 'order-details/'
            })
            .when('/order-history', {
                controller: 'OrderHistoryController',
                templateUrl: 'order-history/'
            })
            .when('/signup', {
                controller: 'SignUpController',
                templateUrl: 'signup/'
            })
            .when('/login', {
                templateUrl: 'login/'
            })
            .otherwise(
            '/demonstration'
        );
    });