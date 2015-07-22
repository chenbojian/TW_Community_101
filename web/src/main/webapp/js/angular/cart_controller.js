var App = angular.module('App', ['ngCookies']);

App.controller('CartController', function($scope, $http, $cookies) {

    $scope.message = '';
    $scope.is_fake = true;
    $scope.webapi_goods_simple_info = "/web/api/customer/goods/simple";
    $scope.webapi_order_submit = "/web/api/customer/submit";
    $scope.selected_items_cookie_key = "allgoods";
    $scope.selected_items_id = [];
    $scope.selected_items_quantity = [];
    $scope.selected_items = [];
    $scope.submit_string = "";

    var get_items_list = function() {
        var encoded_string = $cookies.get($scope.selected_items_cookie_key).toString();
        var item_list = [];
        for (var i = 0, j = -1; i < encoded_string.length; i++) {
            if (encoded_string[i] === '|') {
                item_list.push('');
                j++;
                continue;
            }
            item_list[j] = item_list[j] + encoded_string[i];
        }
        for (var i = 0; i < item_list.length; i++) {
            var details = item_list[i].split(',');
            $scope.selected_items_id.push(parseFloat(details[0]));
            $scope.selected_items_quantity.push(parseFloat(details[1]));
        }
    };
    get_items_list();

    var get_items_info = function() {
        for (var i = 0, len = $scope.selected_items_id.length; i < len; i++) {
            $http.get($scope.webapi_goods_simple_info + "?id=" + $scope.selected_items_id[i]).success(function(data, status, headers, config) {
                var item = data;
                item["quantity"] = $scope.selected_items_quantity[$scope.selected_items.length];
                $scope.selected_items.push(item);
            });
        }
    };
    get_items_info();

    $scope.remove_selected_item = function(index) {
        $scope.selected_items.splice(index, 1);
    };

    $scope.more_selected_item = function(index) {
        $scope.selected_items[index].quantity = $scope.selected_items[index].quantity + 1;
    };

    $scope.less_selected_item = function(index) {
        if ($scope.selected_items[index].quantity > 0) {
            $scope.selected_items[index].quantity = $scope.selected_items[index].quantity - 1;
        }
    };

    $scope.bill = {};
    $scope.bill.total = 0;
    var calculateTotals = function() {
        var total = 0;
        for (var i = 0, len = $scope.selected_items.length; i < len; i++) {
            total = total + $scope.selected_items[i].price * $scope.selected_items[i].quantity;
        }
        $scope.bill.total = total;
    };

    $scope.$watch('selected_items', calculateTotals, true);

    $scope.phone = "";
    $scope.address = "";

    $scope.order_id = "";
    $scope.expires_date = new Date(2099, 9, 9);
    $scope.can_submit_order = true;
    $scope.order_submitted = false;

    $scope.submit = function() {
        var order = {};
        var id_string = "ids=";
        var quantity_string = "quantities=";
        order.goodsList = [];
        for (var i = 0, len = $scope.selected_items.length; i < len; i++) {
            id_string = id_string + $scope.selected_items[i].id;
            quantity_string = quantity_string + $scope.selected_items[i].quantity;
            if (i !== len - 1) {
                id_string = id_string + ',';
                quantity_string = quantity_string + ',';
            }
            order.goodsList.push({"id": $scope.selected_items[i].id, "quantity": $scope.selected_items[i].quantity});
        }
        order.phone = $scope.phone;
        order.address = $scope.address;

        $scope.submit_string = "?" + id_string + '&' + quantity_string + '&phone=' + $scope.phone + '&address=' + $scope.address;

        $http.post($scope.webapi_order_submit + $scope.submit_string).success(function(data, status, headers) {
            $scope.order_id = data;
            $scope.can_submit_order = false;
            $scope.order_submitted = true;
            $cookies.put("orderId", $scope.order_id, {'path': '/web/', 'expires':$scope.expires_date});
            $cookies.put($scope.selected_items_cookie_key,'', {'path': '/web'});
        });
    };
});
