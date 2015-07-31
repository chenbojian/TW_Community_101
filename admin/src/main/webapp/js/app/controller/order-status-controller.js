/**
 * Created by chenjian on 7/31/15.
 */
angular.module("adminApp")
    .controller("orderStatusController", function($scope, $http){
        $scope.newOrdersUrl = $env.contextPath + "/api/order/newOrders";
        $scope.dispatchingOrdersUrl = $env.contextPath + "/api/order/dispatchingOrders";
        $scope.completedOrdersUrl = $env.contextPath + "/api/order/completedOrders";

        $scope.new_list = null;
        $scope.new_num = 0;
        $scope.new_total_price = 0;
        $scope.new_goods_list = null;

        $scope.dispatching_list = null;
        $scope.dispatching_num = 0;
        $scope.dispatching_total_price = 0;
        $scope.dispatching_goods_list = null;

        $scope.completed_list = null;
        $scope.completed_num = 0;
        $scope.completed_total_price = 0;
        $scope.completed_goods_list = null;

        $scope.orders_total_price = 0;
        $scope.orders_total_num = 0;

        var get_new_orders_list = function () {
            $http.get($scope.newOrdersUrl).success(function (data) {
                $scope.new_list = data;
                $scope.new_num = $scope.new_list.length;
                $scope.orders_total_num += $scope.new_num;

                for (var i = 0; i < $scope.new_num; i++) {
                    $scope.new_goods_list = $scope.new_list[i]["goodsInOrderDTOList"];
                    for (var j = 0; j < $scope.new_goods_list.length; j++) {
                        $scope.new_total_price += $scope.new_goods_list[j]["price"] * $scope.new_goods_list[j]["quantity"];
                    }
                }
                $scope.orders_total_price += $scope.new_total_price;
            });
        };
        var get_dispatching_orders_list = function () {
            $http.get($scope.dispatchingOrdersUrl).success(function (data) {
                $scope.dispatching_list = data;
                $scope.dispatching_num = $scope.dispatching_list.length;
                $scope.orders_total_num += $scope.dispatching_num;

                for (var i = 0; i < $scope.dispatching_num; i++) {
                    $scope.dispatching_goods_list = $scope.dispatching_list[i]["goodsInOrderDTOList"];
                    for (var j = 0; j < $scope.dispatching_goods_list.length; j++) {
                        $scope.dispatching_total_price += $scope.dispatching_goods_list[j]["price"] * $scope.dispatching_goods_list[j]["quantity"];
                    }
                }
                $scope.orders_total_price += $scope.dispatching_total_price;
            });
        };
        var get_completed_orders_list = function () {
            $http.get($scope.completedOrdersUrl).success(function (data) {
                $scope.completed_list = data;
                $scope.completed_num = $scope.completed_list.length;
                $scope.orders_total_num += $scope.completed_num;

                for (var i = 0; i < $scope.completed_num; i++) {
                    $scope.completed_goods_list = $scope.completed_list[i]["goodsInOrderDTOList"];
                    for (var j = 0; j < $scope.completed_goods_list.length; j++) {
                        $scope.completed_total_price += $scope.completed_goods_list[j]["price"] * $scope.completed_goods_list[j]["quantity"];
                    }
                }
                $scope.orders_total_price += $scope.completed_total_price;
            });
        };

        get_new_orders_list();
        get_dispatching_orders_list();
        get_completed_orders_list();
    });