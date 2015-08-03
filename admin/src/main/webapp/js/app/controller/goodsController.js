angular.module("adminApp")
    .controller("goodsController", ['$scope', 'FileUploader', function ($scope, FileUploader) {
        var uploader = $scope.uploader = new FileUploader({
            url: 'xxx'
        });

        $scope.isUseLocalPicture = false;

        $scope.useRemotePicture = function () {
            $scope.isUseLocalPicture = !$scope.isUseLocalPicture;
        };

        $scope.useLocalPicture = function () {
            $scope.isUseLocalPicture = !$scope.isUseLocalPicture;
        };

    }]);