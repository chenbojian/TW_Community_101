angular.module('webApp')
    .controller('OrderHistoryController', function($scope, $http, $cookies, $resource) {

    var orderHistoryResource = $resource(env.contextPath + "/api/order/history");
    $scope.order_list = orderHistoryResource.query();

    $scope.message = '';
    $scope.order_list = null;

    $scope.getOrderGoodsDetail = functions.getOrderGoodsDetail;

    $scope.launchOrderDetails = function() {
        $cookies.put('orderId', this.order.orderId.toString(), {'path': env.contextPath + '/'});
        window.location.assign('#/order-details');
    }
});
