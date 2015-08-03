angular.module('webApp')
.service('SharedFunctions', function() {
        var allgoods = "";
        var orderId = "";
        return {
            getAllGoods: function() {
                return allgoods;
            },
            setAllGoods: function(value) {
                allgoods = value;
            },
            getOrderId: function() {
                return orderId;
            },
            setOrderId: function(value) {
                orderId = value;
            },
        getOrderGoodsDetail: function() {

            var url = env.contextPath + "/api/order/goods?" + "&goodsId=" + this.goods.id;

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
        },
        getGoodsDetail: function(gid) {

            var url = env.contextPath + "/api/goods/details?id=" + gid;

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
        }
    });