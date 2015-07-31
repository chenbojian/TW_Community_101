/**
 * Created by chenjian on 7/21/15.
 */
angular.module("adminApp")
    .controller("orderManagerController", function ($scope, $http) {

        $scope.newOrdersUrl = $env.contextPath + "/api/order/newOrders";
        $scope.dispatchingOrdersUrl = $env.contextPath + "/api/order/dispatchingOrders";
        $scope.completedOrdersUrl = $env.contextPath + "/api/order/completedOrders";

        $scope.dispatchOrderUrl = $env.contextPath + "/api/order/dispatchOrder";
        $scope.completeOrderUrl = $env.contextPath + "/api/order/completeOrder";

        $scope.newOrders = [];
        $scope.dispatchingOrders = [];
        $scope.completedOrders = [];

        $scope._title = document.title;
        $scope.sound = document.getElementById('sound');

        var getNewOrders = function () {
            $http.get($scope.newOrdersUrl).success(function (data, status, headers, config) {
                $scope.newOrders = data;
            });
        };

        var getDispatchingOrders = function () {
            $http.get($scope.dispatchingOrdersUrl).success(function (data, status, headers, config) {
                $scope.dispatchingOrders = data;
            });
        };

        var getCompletedOrders = function () {
            $http.get($scope.completedOrdersUrl).success(function (data, status, headers, config) {
                $scope.completedOrders = data;
            });
        };

        $scope.toFixed = function (price) {  //保留2位小数
            return price.toFixed(2);
        }

        //  打开页面初始化订单列表
        getCompletedOrders();
        getDispatchingOrders();
        getNewOrders();

        $scope.dispatchOrder = function (orderId) {
            $http.get($scope.dispatchOrderUrl + "?orderId=" + orderId).success(function (data, status, headers, config) {
                var is_done = data;
                if (!is_done) {
                    alert('订单已在别处被操作！请勿重复操作。');
                }
                getNewOrders();
                getDispatchingOrders();  //刷新派送中的订单
            });
        };

        $scope.completeOrder = function (orderId) {
            $http.get($scope.completeOrderUrl + "?orderId=" + orderId).success(function (data, status, headers, config) {
                var is_done = data;
                if (!is_done) {
                    alert('订单已在别处被操作！请勿重复操作。');
                }
                getDispatchingOrders();
                getCompletedOrders();    //刷新已完成的订单
            });
        };

        var init_desktop_nofitication = function () {
            if (!("Notification" in window)) {
                alert("此浏览器不支持桌面通知。");
            }

            else if (Notification.permission === "granted") {
                //var notification = new Notification("已获得桌面通知权限。");
            }

            else if (Notification.permission !== 'denied') {
                Notification.requestPermission(function (permission) {
                    if (permission === "granted") {
                        var notification = new Notification("获得桌面通知权限");
                    }
                });
            }

            // At last, if the user has denied notifications, and you
            // want to be respectful there is no need to bother them any more.
        };
        init_desktop_nofitication();

        $scope.show_desktop_notification = function (message) {
            if (Notification.permission === 'granted') {
                var notification = new Notification(message);
            }
        };

        var show = function () { //有新消息时在title处闪烁提示
            var step = 0;

            var timer = setInterval(function () {
                step++;
                if (step == 3) {
                    step = 1
                }
                ;
                if (step == 1) {
                    document.title = '【　　　　　】' + $scope._title
                }
                ;
                if (step == 2) {
                    document.title = '【您有新订单】' + $scope._title
                }
                ;
            }, 500);

            setTimeout(function () {
                clearInterval(timer);
                document.title = $scope._title;
            }, 10000);
        };

        var hasNewOrder = function () {
            $http.get($env.contextPath + "/api/order/hasNewOrder").success(function (data, status, headers, config) {
                if (data.hasNewOrders == "yes") {
                    show();
                    $scope.show_desktop_notification('有新订单！');
                    $scope.sound.play();
                    getNewOrders();
                }
            });
        };

        setInterval(hasNewOrder, 5000);

        $scope.getGoodsDetail = function () {

            var url = $env.contextPath + "/api/customer/order/goods?" + "&goodsId=" + this.goods.id;

            $.ajax({
                url: url,
                type: "get",
                success: function (data) {
                    var html = $("#goodsDetail").html();
                    html = html.replace("[goodsname]", data.name);
                    html = html.replace("[goodsdescription]", data.description);
                    html = html.replace("[goodsprice]", data.price / 100);
                    //alert(html);

                    $("#goodsDetail").html(html);
                    $("#goodsDetail").modal();
                    $("#goodsDetailName").html(data.name);
                    $("#goodsDetailDescription").html(data.description);
                    var price = data.price / 100;
                    $("#goodsDetailPrice").html(price.toFixed(2));
                    $("#goodsDetailPicture").attr("src", data.pic);

                }
            });
        }
    });