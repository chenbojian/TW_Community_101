;$(function () {
    var $goodslist = $("#goodslist");
    $goodslist.delegate("div.btn-sm", "click", function() {
        var $this = $(this);
        var gid = $this.data("gid");//attr("data-gid");
        getGoodsDetail(gid);
    });
    $goodslist.undelegate(".goodsminus");
    $goodslist.undelegate(".goodsplus");
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
});

//api interface
function getGoods(cid) {

    var url = "/web/api/customer/goods?cid="+cid;

    var $goodslist = $("#goodslist");
    $goodslist.html('');
    $.ajax({
        url: url,
        type: 'get',
        success: function (data) {
            var htmlTemplate = '<div class="col-md-3">\
                                        <div class="thumbnail">\
                                            <img src="[goodspic]" />\
                                            <div class="caption text-center">\
                                                <h5>[goodsname]</h5>\
                                                <h4 class="pull-left text-danger">[goodsprice]</h4>\
                                                <div class="btn-group pull-right" role="group">\
                                                    <button type="button" class="btn btn-sm btn-default goodsminus">-</button>\
                                                    <div class="btn btn-sm btn-default goodsitem" data-gid="[goodsid]">0</div>\
                                                    <button type="button" class="btn btn-sm btn-default goodsplus">+</button>\
                                                </div>\
                                                <div class="clearfix"></div>\
                                            </div>\
                                        </div>\
                                    </div>';

            for (var i = 0; i < data.length; i++) {
                var html = htmlTemplate.replace("[goodspic]", data[i].pic)
                    .replace("[goodsname]", data[i].name)
                    .replace("[goodsprice]", data[i].price/100)
                    .replace("[goodsid]", data[i].id);

                var $last = $goodslist.children(":last");

                if ($last.length == 0) {
                    $goodslist.html(html);
                }
                else {
                    $last.after(html);
                }
            }
            initGoodsCount();
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
            //data = [
            //    { "id": "1", "name": "食品" },
            //    { "id": "2", "name": "日用品" },
            //    { "id": "3", "name": "电器" }
            //];
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
        success:function(data) {
            var html = $("#goodsDetail").html();
            html = html.replace("[goodsname]", data.name);
            html = html.replace("[goodsdescription]", data.description);
            html = html.replace("[goodsprice]", data.price/100);
            //alert(html);

            $("#goodsDetail").html(html);
            $("#goodsDetail").modal();
        }
    });
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
    $.cookie(allgoodscookiename, all);
}
function initGoodsCount() {
    var allgoodscookiename = "allgoods";
    var all = $.cookie(allgoodscookiename);
    var list = (all+"").split('|');
    for (var i = 0; i < list.length; i++) {
        var $item = $(".goodsitem");
        for (var j = 0; j < $item.length; j++) {
            if ($($item[j]).data('gid') == list[i].split(',')[0]) {
                $($item[j]).html(list[i].split(',')[1]);
                break;
            }
        }

    }
}

