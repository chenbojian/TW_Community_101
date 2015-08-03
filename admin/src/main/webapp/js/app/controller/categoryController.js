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

        $scope.isEditCategory = {};
        $scope.clickEditButton = function (category) {
            $scope.isEditCategory[category.id] = !$scope.isEditCategory[category.id];
        };
        $scope.clickCompleteButton = function (category) {
            $scope.isEditCategory[category.id] = !$scope.isEditCategory[category.id];
            categoryResource.save({categoryId:category.id},category);

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