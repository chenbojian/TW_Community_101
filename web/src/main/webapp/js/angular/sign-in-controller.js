/**
 * Created by chenjian on 7/27/15.
 */
var app = angular.module("App",[]);

app.controller("signInController", function($scope, $http) {
    $scope.tel_phone = "";
    $scope.password = "";
    $scope.password_repeat = "";
    $scope.SMS_code = "";
    $scope.password_msg = "1";
    $scope.msg = "";

    $scope.sign_in_url = "/web/api/customer/signIn";

    $scope.$watch("password_repeat", function(newValue, oldValue, scope){
        if($scope.password != $scope.password_repeat){
            $scope.password_msg = "密码不一致";
        }else $scope.password_msg = ""
    },false);

    $scope.send_SMS_code = function(){

    }

    $scope.sign_in = function(){
        if($scope.password==$scope.password_repeat){
            var params = {
                    "tel_phone":$scope.tel_phone,
                    "password":$scope.password,
                    "SMS_code":$scope.SMS_code
            };
            //window.alert(params);
            $http({
                method: 'POST',
                url: $scope.sign_in_url,
                data: params,
                headers: {'Content-Type': 'application/json'}
            }).success(function(data){
                window.alert("sign in successful");
            });
            return;
        }else {
            window.alert("密码不一致，请重新输入");
            $scope.password = "";
            $scope.password_repeat = "";
        }
    };
});
