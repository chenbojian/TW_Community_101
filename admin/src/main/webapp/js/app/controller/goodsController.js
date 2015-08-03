angular.module("adminApp")
    .controller("goodsController", ['$scope', 'FileUploader', function ($scope, FileUploader) {
        var uploader = $scope.uploader = new FileUploader({
            url: 'xxx'
        })

    }]);