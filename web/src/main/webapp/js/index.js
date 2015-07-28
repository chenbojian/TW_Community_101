$(function () {
    var $goodslist = $("#goodslist");
    $goodslist.delegate(".caption ", "click", function () {
        var $this = $(this);
        var gid = $this.data("gid");//attr("data-gid");
        getGoodsDetail(gid);
    });

    $goodslist.delegate(".goodsminus", "click", function () {
        var $next = $(this).next();
        var num = parseInt($next.html());
        var gid = $next.data('gid');
        if (num > 0) {
            num--;
            $next.html(num);
        }
        updateCookie(gid, num);
    });
    $goodslist.delegate(".goodsplus", "click", function () {
        var $prev = $(this).prev();
        var num = parseInt($prev.html());
        var gid = $prev.data('gid');
        num++;
        $prev.html(num);
        updateCookie(gid, num);
    });

    getCategory();
    getGoods(0);

    clearCookie();
});

//api interface
function getGoodsNumByIdFromCookie(cid) {
    var allGoodsNumStr = $.cookie("allgoods");
    var allGoodsList = allGoodsNumStr.split("|");
    var goodsAndNum = {};
    allGoodsList.forEach(function (goodsWithNum) {
        if (goodsWithNum.indexOf(",") != -1) {
            var kv = goodsWithNum.split(",");
            goodsAndNum[kv[0]] = kv[1];
        }
    });
    if(goodsAndNum.hasOwnProperty(cid.toString())){
        return goodsAndNum[cid.toString()];
    }
    return 0;
}
function getGoods(cid) {

    var url = "/web/api/customer/goods?cid=" + cid;

    var $goodslist = $("#goodslist");
    $goodslist.html('');
    $.ajax({
        url: url,
        type: 'get',
        success: function (data) {
            var htmlTemplate = '<div class="col-md-3">\
                                        <div class="thumbnail">\
                                            <div class="caption text-center" data-gid="[goodsid]">\
                                                <img src="[goodspic]" />\
                                                <h5>[goodsname]</h5>\
                                                <h4 class="pull-left text-danger">[goodsprice]</h4>\
                                                <div class="clearfix"></div>\
                                            </div>\
                                        </div>\
                                        <div class="btn-group pull-right" role="group" style="position: absolute;right:30px;bottom:30px;">\
                                            <button type="button" class="btn btn-sm btn-default goodsminus">-</button>\
                                            <div class="btn btn-sm btn-default goodsitem" data-gid="[goodsid]">[goodsnum]</div>\
                                            <button type="button" class="btn btn-sm btn-default goodsplus">+</button>\
                                        </div>\
                                    </div>';

            for (var i = 0; i < data.length; i++) {

                var html = htmlTemplate.replace("[goodspic]", data[i].pic)
                    .replace("[goodsname]", data[i].name)
                    .replace("[goodsprice]", data[i].price / 100)
                    .replace("[goodsid]", data[i].id)
                    .replace("[goodsid]", data[i].id)
                    .replace("[goodsnum]", getGoodsNumByIdFromCookie(data[i].id));


                var $last = $goodslist.children(":last");

                if ($last.length == 0) {
                    $goodslist.html(html);
                }
                else {
                    $last.after(html);
                }
            }
        }
    });

}
function getCategory() {

    var url = "/web/api/customer/categories";

    var $categorylist = $("#categorylist");
    $.ajax({
        url: url,
        type: 'get',
        success: function (data) {
            var htmlTemplate = '<div data-cid="[categoryid]" class="list-group-item">[categoryName]</div>';
            for (var i = 0; i < data.length; i++) {
                var link = "" + data[i].id + "";
                var html = htmlTemplate
                    .replace("[categoryid]", data[i].id)
                    .replace("[categoryUrl]", link)
                    .replace("[categoryName]", data[i].name);
                var $last = $categorylist.children(":last");
                $last.after(html);
            }

        }
    });

    $categorylist.delegate('div', 'click', function () {
        getGoods($(this).data("cid"));
        $categorylist.find("div").removeClass("active");
        $(this).addClass("active");
    });
}
function getGoodsDetail(gid) {

    var url = "/web/api/customer/goods/details?id=" + gid;

    $.ajax({
        url: url,
        type: "get",
        success: function (data) {
            var html = $("#goodsDetail").html();
            html = html.replace("[goodsname]", data.name);
            html = html.replace("[goodsdescription]", data.description);
            html = html.replace("[goodsprice]", data.price / 100);
            html = html.replace("[goodsnum]", getGoodsNumByIdFromCookie(data.id));
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

function clearCookie() {
    $.cookie("allgoods", "", {path: '/web/'});
}
function updateCookie(gid, num) {
    var allgoodscookiename = "allgoods";
    var all = $.cookie(allgoodscookiename);
    var result = gid + "," + num;
    if (all == undefined || all == "undefined") {
        all = result;
    } else {
        var glist = all.split('|');
        var isExist = false;
        for (var i = 0; i < glist.length; i++) {
            var goodsid = (glist[i] + "").split(',')[0];
            if (goodsid == gid) {
                isExist = true;
                if (num == 0) {
                    glist.splice(i, 1);
                } else {
                    glist[i] = result;
                }
                break;
            }
        }

        all = glist.join("|");
        if (!isExist) {
            all += "|" + result;
        }
    }
    $.cookie(allgoodscookiename, all, {path: '/web/'});
}

