angular.module('webApp')
    .controller('OrderHistoryController', function($scope, $http, $cookies, $resource, SharedFunctions) {

    $scope.message = '';

    var orderHistoryResource = $resource(env.contextPath + "/api/order/history");
    $scope.order_list = orderHistoryResource.query();
        
    $scope.getOrderGoodsDetail = SharedFunctions.getOrderGoodsDetail;

    $scope.launchOrderDetails = function() {
        $cookies.put('orderId', this.order.orderId.toString(), {'path': env.contextPath + '/'});
        //SharedProperties.setOrderId(this.order.orderId.toString());
        window.location.assign('#/order-details');
    }
});
