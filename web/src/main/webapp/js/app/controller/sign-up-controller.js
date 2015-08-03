angular.module("webApp")
    .controller("SignUpController", function($scope, $http) {

    $scope.tel_phone = "";
    $scope.password = "";
    $scope.password_repeat = "";
    $scope.SMS_code = "";
    $scope.password_msg = "1";
    $scope.msg = "";
    $scope.send_msg_btn = "发送短信验证码";

    $scope.sign_in_url = env.contextPath + "/api/customer/signup";

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
                    "telPhone":$scope.tel_phone,
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
