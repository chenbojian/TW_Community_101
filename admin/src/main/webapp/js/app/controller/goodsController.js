angular.module("adminApp")
    .controller("goodsController", ['$scope', '$http', 'FileUploader', function ($scope, $http, FileUploader) {
        $scope.goods = {};
        $http.get('api/category').success(function (data) {
            $scope.categories = data;
        });

        var uploader = $scope.uploader = new FileUploader({
            url: 'api/goods/picture/upload'
        });

        $scope.isUseLocalPicture = false;

        $scope.useRemotePicture = function () {
            $scope.isUseLocalPicture = !$scope.isUseLocalPicture;
        };

        $scope.useLocalPicture = function () {
            $scope.isUseLocalPicture = !$scope.isUseLocalPicture;
        };

        uploader.onSuccessItem = function (item, response) {
            $scope.goods.pictureUrl = response
        };
        $scope.submitNewGoods = function () {
            $scope.goods.price = $scope.goods.price * 100;
            $http.post('api/goods/add', $scope.goods)
                .success(function () {
                    $scope.goods = {};
                    $scope.addGoodsMessage = "Add goods successful!"
                })
                .error(function () {
                    $scope.addGoodsMessage = "Have errors!"
                })
        }
    }]);
