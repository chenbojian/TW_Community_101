/**
 * Created by chenjian on 7/27/15.
 */
var app = angular.module("App",[]);

app.controller("loginController", function($scope, $http) {
    $scope.tel_phone = "";
    $scope.password = "";

    $scope.login_url = "/web/api/customer/login";

    $scope.login = function(){
        var params = {
            "telPhone":$scope.tel_phone,
            "password":$scope.password
        }
        $http({
            method: 'POST',
            url: $scope.login_url,
            data: params,
            headers: {'Content-Type': 'application/json'}
        }).success(function(data){
            window.alert("sign in successful");
        });
    };
});