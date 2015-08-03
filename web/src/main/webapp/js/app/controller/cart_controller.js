angular.module('webApp')
    .controller('CartController', function($scope, $http, $cookies, SharedProperties) {

    $scope.message = '';
    $scope.is_fake = true;
    $scope.webapi_goods_simple_info = env.contextPath + "/api/goods/summary";
    $scope.webapi_order_submit = env.contextPath + "/api/order/submit";
    $scope.selected_items_cookie_key = "allgoods";
    $scope.selected_items_id = [];
    $scope.selected_items_quantity = [];
    $scope.selected_items = [];
    $scope.submit_string = "";


    var get_items_list = function() {
        //var encoded_string = $cookies.get($scope.selected_items_cookie_key).toString();
        var encoded_string = SharedProperties.getAllGoods();
        var item_list = encoded_string.split('|');

        for (var i = 0; i < item_list.length; i++) {
            var details = item_list[i].split(',');
            if (parseFloat(details[1]) > 0) {
                $scope.selected_items_id.push(parseFloat(details[0]));
                $scope.selected_items_quantity.push(parseFloat(details[1]));
            }
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
        $scope.selected_items[index].quantity = parseInt($scope.selected_items[index].quantity) + 1;
    };

    $scope.less_selected_item = function(index) {
        if ($scope.selected_items[index].quantity > 0) {
            $scope.selected_items[index].quantity = parseInt($scope.selected_items[index].quantity) - 1;
        }
    };

    $scope.getGoodsDetail = functions.getGoodsDetail;

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

    var validateGoodsQuantity = function() {
        for (var i = 0, len = $scope.selected_items.length; i < len; i++) {
            if(!($scope.selected_items[i].quantity >= 0)) {
                $scope.selected_items[i].quantity = 0;
            }
        }
    };

    $scope.$watch('selected_items', validateGoodsQuantity, true);

    $scope.phone = "";
    $scope.address = "";

    $scope.order_id = "";
    $scope.expires_date = new Date(2099, 9, 9);
    $scope.can_submit_order = true;
    $scope.order_submitted = false;

    $scope.submit = function() {
        var id_string = "ids=";
        var quantity_string = "quantities=";
        for (var i = 0, len = $scope.selected_items.length; i < len; i++) {
            id_string = id_string + $scope.selected_items[i].id;
            quantity_string = quantity_string + $scope.selected_items[i].quantity;
            if (i !== len - 1) {
                id_string = id_string + ',';
                quantity_string = quantity_string + ',';
            }
        }

        $scope.submit_string = "?" + id_string + '&' + quantity_string + '&phone=' + $scope.phone + '&address=' + $scope.address;

        $http.post($scope.webapi_order_submit + $scope.submit_string).success(function(data, status, headers) {
            $scope.order_id = data.orderId;
            if ($scope.order_id !== 0) {
                $scope.message = "";
                $scope.can_submit_order = false;
                $scope.order_submitted = true;
                $cookies.put("orderId", $scope.order_id, {'path': env.contextPath + '/', 'expires':$scope.expires_date});
                $cookies.put($scope.selected_items_cookie_key,'', {'path': env.contextPath + '/'});
            }
            else {
                var error_messages = data.errorMessages;
                $scope.message = "无法下单。";
                for (var i = 0, len = error_messages.length; i < len; i++) {
                    var j = i;
                    $scope.message = $scope.message + error_messages[j];
                }
            }
        });
    };
});
