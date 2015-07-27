/**
 * Created by chenjian on 7/21/15.
 */
var App = angular.module("App",[]);

App.controller("orderManagerController", function($scope, $http){

    $scope.newOrdersUrl = "/web/api/order/newOrders";
    $scope.dispatchingOrdersUrl = "/web/api/order/dispatchingOrders";
    $scope.completedOrdersUrl = "/web/api/order/completedOrders";

    $scope.dispatchOrderUrl = "/web/api/order/dispatchOrder";
    $scope.completeOrderUrl = "/web/api/order/completeOrder";

    $scope.newOrders = [];
    $scope.dispatchingOrders = [];
    $scope.completedOrders = [];

    $scope._title = document.title;
    $scope.sound = document.getElementById('sound');

    $scope.new_list=null;
    $scope.new_num=0;
    $scope.new_total_price=0;
    $scope.new_goods_list=null;

    $scope.dispatching_list=null;
    $scope.dispatching_num=0;
    $scope.dispatching_total_price=0;
    $scope.dispatching_goods_list=null;

    $scope.completed_list=null;
    $scope.completed_num=0;
    $scope.completed_total_price=0;
    $scope.completed_goods_list=null;

    $scope.orders_total_price=0;
    $scope.orders_total_num=0;

    var getNewOrders = function() {
        $http.get($scope.newOrdersUrl).success(function(data, status, headers, config){
            $scope.newOrders = data;
        });
    };

    var getDispatchingOrders = function() {
        $http.get($scope.dispatchingOrdersUrl).success(function(data, status, headers, config){
            $scope.dispatchingOrders = data;
        });
    };

    var getCompletedOrders = function(){
        $http.get($scope.completedOrdersUrl).success(function(data, status, headers, config){
            $scope.completedOrders = data;
        });
    };

    $scope.toFixed = function(price){  //保留2位小数
        return price.toFixed(2);
    }

    //  打开页面初始化订单列表
    getCompletedOrders();
    getDispatchingOrders();
    getNewOrders();

    $scope.dispatchOrder = function(index){
        var orderId = $scope.newOrders[index].orderId;
        $http.get($scope.dispatchOrderUrl+"?orderId="+orderId).success(function(data, status, headers, config){
            $scope.newOrders.splice(index, 1);

            getDispatchingOrders();  //刷新派送中的订单

        });
    };

    $scope.completeOrder = function(index){
        var orderId = $scope.dispatchingOrders[index].orderId;
        $http.get($scope.completeOrderUrl+"?orderId="+orderId).success(function(data, status, headers, config){
            $scope.dispatchingOrders.splice(index, 1);

            getCompletedOrders();    //刷新已完成的订单
        });
    };

    var init_desktop_nofitication = function() {
        // Let's check if the browser supports notifications
        if (!("Notification" in window)) {
            alert("此浏览器不支持桌面通知。");
        }

        // Let's check whether notification permissions have alredy been granted
        else if (Notification.permission === "granted") {
            // If it's okay let's create a notification
            var notification = new Notification("已获得桌面通知权限。");
        }

        // Otherwise, we need to ask the user for permission
        else if (Notification.permission !== 'denied') {
            Notification.requestPermission(function (permission) {
                // If the user accepts, let's create a notification
                if (permission === "granted") {
                    var notification = new Notification("获得桌面通知权限");
                }
            });
        }

        // At last, if the user has denied notifications, and you
        // want to be respectful there is no need to bother them any more.
    };
    init_desktop_nofitication();

    $scope.show_desktop_notification = function(message) {
        if (Notification.permission === 'granted') {
            var notification = new Notification(message);
        }
    };

    var show = function() { //有新消息时在title处闪烁提示
        var step=0;

        var timer = setInterval(function() {
            step++;
            if (step==3) {step=1};
            if (step==1) {document.title='【　　　　　】'+$scope._title};
            if (step==2) {document.title='【您有新订单】'+$scope._title};
        }, 500);

        setTimeout(function(){
            clearInterval(timer);
            document.title = $scope._title;
        }, 10000);
    };

    $scope.$watch("newOrders", function(newValue, oldValue, scope){  //监测新订单列表，有新订单的话闪烁标题并发出提示音
        if(newValue.length>oldValue.length){
            show();
            $scope.show_desktop_notification('有新订单！');
            $scope.sound.play();
        }
    },true);

    setInterval(getNewOrders, 5000);


    var get_new_orders_list=function(){
        $http.get($scope.newOrdersUrl).success(function(data){
            $scope.new_list=data;
            $scope.new_num=$scope.new_list.length;
            $scope.orders_total_num+=$scope.new_num;

            for(var i=0;i<$scope.new_num;i++){
                $scope.new_goods_list=$scope.new_list[i]["goodsInOrderDTOList"];
                for(var j=0;j<$scope.new_goods_list.length;j++){
                    $scope.new_total_price+=$scope.new_goods_list[j]["price"]*$scope.new_goods_list[j]["quantity"];
                }
            }
            $scope.orders_total_price+=$scope.new_total_price;
        });
    };
    var get_dispatching_orders_list=function(){
        $http.get($scope.dispatchingOrdersUrl).success(function(data){
            $scope.dispatching_list=data;
            $scope.dispatching_num=$scope.dispatching_list.length;
            $scope.orders_total_num+=$scope.dispatching_num;

            for(var i=0;i<$scope.dispatching_num;i++){
                $scope.dispatching_goods_list=$scope.dispatching_list[i]["goodsInOrderDTOList"];
                for(var j=0;j<$scope.dispatching_goods_list.length;j++){
                    $scope.dispatching_total_price+=$scope.dispatching_goods_list[j]["price"]*$scope.dispatching_goods_list[j]["quantity"];
                }
            }
            $scope.orders_total_price+=$scope.dispatching_total_price;
        });
    };
    var get_completed_orders_list=function(){
        $http.get($scope.completedOrdersUrl).success(function(data){
            $scope.completed_list=data;
            $scope.completed_num=$scope.completed_list.length;
            $scope.orders_total_num+=$scope.completed_num;

            for(var i=0;i<$scope.completed_num;i++){
                $scope.completed_goods_list=$scope.completed_list[i]["goodsInOrderDTOList"];
                for(var j=0;j<$scope.completed_goods_list.length;j++){
                    $scope.completed_total_price+=$scope.completed_goods_list[j]["price"]*$scope.completed_goods_list[j]["quantity"];
                }
            }
            $scope.orders_total_price+=$scope.completed_total_price;
        });
    };

    get_new_orders_list();
    get_dispatching_orders_list();
    get_completed_orders_list();

    $scope.getGoodsDetail = function() {

        var url = "/web/api/customer/order/goods?" + "&goodsId=" + this.goods.id;

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

});
