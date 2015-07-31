angular.module('adminApp')
    .controller('categoryController', function ($scope, $http, $resource) {
        var categoryResource = $resource('api/category/:categoryId');
        $scope.categories = categoryResource.query();

        $scope.deleteCategory = function (categoryId) {
            categoryResource.delete({categoryId: categoryId}, function () {
                $scope.categories = _($scope.categories).reject(function (category) {
                    return category.id == categoryId;
                })
            })
        };

        $scope.category = {};
        $scope.createCategory = function () {
            $http.post('api/category/add', $scope.category)
                .success(function (id) {
                    $scope.category.id = id;
                    $scope.categories.push($scope.category);
                    $scope.category = {};
                });
        }

    });