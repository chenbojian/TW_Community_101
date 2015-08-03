angular.module('webApp')
    .controller('IndexController', function($scope, $http, $cookies, $resource) {
    $scope.goods_quantities_key = 'allgoods';
    (function clearCookie () {
        $cookies.put($scope.goods_quantities_key, "", {path: env.contextPath + '/'});
    })();

    var categoryResource = $resource(env.contextPath + "/api/goods/categories");
    $scope.categories = categoryResource.query();

    $scope.active_category_id = 0;

    $scope.select_category = function(cid) {
        $scope.getGoods(cid);
        $scope.active_category_id = cid;
    };

    $scope.webapi_goods_summary = env.contextPath + "/api/goods/";
    $scope.goods_to_list = [];

    $scope.getGoodsQuantityCookie = function(gid) {
        var allgoods = $cookies.get($scope.goods_quantities_key);
        var goodslist = allgoods.split('|');
        for (var i = 0, len = goodslist.length; i < len; i++) {
            var details = goodslist[i].split(',');
            if (parseFloat(details[0]) === gid) {
                return parseFloat(details[1]);
            }
        }
        return 0;
    };

    $scope.getGoods = function(cid) {
        $http.get($scope.webapi_goods_summary + "?cid=" + cid).success(function(data) {
            $scope.goods_to_list = data;
            for (var i = 0, len = $scope.goods_to_list.length; i < len; i++) {
                $scope.goods_to_list[i].quantity = $scope.getGoodsQuantityCookie($scope.goods_to_list[i].id);
            }
        });
    };
    $scope.getGoods(0);

    $scope.setGoodsQuantityCookie = function(gid, quantity) {
        var isRecorded = false;
        var allgoods = $cookies.get($scope.goods_quantities_key);
        if (allgoods === "") {
            allgoods = [gid.toString(), quantity.toString()].join(',');
        }
        else {
            var goodslist = allgoods.split('|');
            for (var i = 0, len = goodslist.length; i < len; i++) {
                var details = goodslist[i].split(',');
                if (parseFloat(details[0]) === gid) {
                    isRecorded = true;
                    details[1] = quantity.toString();
                    goodslist[i] = details.join(',');
                    break;
                } 
            }
            if (isRecorded === false) {
                goodslist.push([gid.toString(), quantity.toString()].join(','));
            }
            allgoods = goodslist.join('|');
        }
        $cookies.put($scope.goods_quantities_key, allgoods, {path: env.contextPath + '/'});
    };

    $scope.setGoodsQuantity = function(gid, quantity) {
        for (var i = 0, len = $scope.goods_to_list.length; i < len; i++) {
            if ($scope.goods_to_list[i].id === gid) {
                $scope.goods_to_list[i].quantity = quantity;
                break;
            }
        }
        $scope.setGoodsQuantityCookie(gid, quantity);
    };

    $scope.goodsPlus = function(gid) {
        var quantity = $scope.getGoodsQuantityCookie(gid) + 1;
        $scope.setGoodsQuantity(gid, quantity);
    };

    $scope.goodsMinus = function (gid) {
        var quantity = $scope.getGoodsQuantityCookie(gid);
        quantity = quantity > 0 ? quantity - 1 : 0;
        $scope.setGoodsQuantity(gid, quantity);
    };

    $scope.getGoodsDetail = $functions.getGoodsDetail;
});
