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
            tel_phone:$scope.tel_phone,
            password:$scope.password
        }
        $http.post($scope.login_url, params, config
            ).success(function(data, status, headers, config){
                window.alert("login successful");
            }).error(function(data, status, headers, config){
                window.alert("login failed");
            });
    };
});