var App = angular.module('App', ['ngCookies']);

App.controller('OrderHistoryController', function($scope, $http, $cookies) {

    $scope.webapi_order_history = $env.contextPath + "/api/customer/orders";

    $scope.message = '';
    $scope.order_list = null;

    var get_order_list = function() {
        $http.get($scope.webapi_order_history).success(function(data) {
            $scope.order_list = data;
        });
    };
    get_order_list();

    $scope.getGoodsDetail = function() {

        var url = $env.contextPath + "/api/customer/order/goods?" + "&goodsId=" + this.goods.id;

        $.ajax({
            url: url,
            type: "get",
            success:function(data) {
                var html = $("#goodsDetail").html();
                html = html.replace("[goodsname]", data.name);
                html = html.replace("[goodsdescription]", data.description);
                html = html.replace("[goodsprice]", data.price/100);
                //alert(html);

                $("#goodsDetail").html(html);
                $("#goodsDetail").modal();
                $("#goodsDetailName").html(data.name);
                $("#goodsDetailDescription").html(data.description);
                var price = data.price/100;
                $("#goodsDetailPrice").html(price.toFixed(2));
                $("#goodsDetailPicture").attr("src",data.pic);

            }
        });
    }

    $scope.launchOrderDetails = function() {
        $cookies.put('orderId', this.order.orderId.toString(), {'path': $env.contextPath + '/'});
        window.location.assign($env.contextPath + '/customer/order-details/');
    }
});
