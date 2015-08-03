angular.module('webApp')
    .factory('GoodsService', function($http, $cookies, $resource) {
        var categoryResource = $resource(env.contextPath + "/api/goods/categories");
        var categories = categoryResource.query();
        alert(JSON.stringify(categories));

        return {
            categories: categories
        };
    });