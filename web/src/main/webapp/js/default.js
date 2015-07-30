/**
 * Created by MiffyLiye on 30/07/2015.
 */
$env = {};
$env.contextPath = '/web';

$functions = {};
$functions.getOrderGoodsDetail = function() {

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
};

$functions.getGoodsDetail = function(gid) {

    var url = $env.contextPath + "/api/customer/goods/details?id=" + gid;

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
};

$functions.logout = function() {
    var url = $env.contextPath + "/customer/logout";

    $.ajax({
        url: url,
        type: "post",
        success:function(data) {
            window.location.assign($env.contextPath + "/");
        }
    });
};