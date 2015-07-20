<%--
  Created by IntelliJ IDEA.
  User: chenjian
  Date: 7/17/15
  Time: 11:59 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
  <title>订单管理</title>
  <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
</head>
<body>
  <ul>
    <li>new orders</li>
    <li>dispatching orders</li>
    <li>complited orders</li>
    <li>canceled orders</li>
  </ul>

  <div id="content">
    <div id="newOrder">
      new order:
      <ul></ul>
    </div>
    <div id="dispatchingOrder">
      dispatching order:
      <ul></ul>
    </div>
    <div id="completed">
      complited order:
      <ul></ul>
    </div>
    <div id="cancelOrder">
      cancel order:
      <ul></ul>
    </div>
  </div>


  <script src="//cdn.bootcss.com/jquery/2.1.4/jquery.min.js"></script>
  <script type="text/javascript">
    function dispatchOrder(){
      var orderId = $(this).id;
      $.get("${pageContext.request.contextPath}/api/order/dispatchOrder"
              ,{"orderId":orderId}
              ,function(){}
      );
    }
    $(function () {
      $.extend({
        /**
         * 调用方法： var timerArr = $.blinkTitle.show();
         * 清除方法： $.blinkTitle.clear(timerArr);
         */
        blinkTitle : {
          show : function() { //有新消息时在title处闪烁提示
            var step=0, _title = document.title;

            var timer = setInterval(function() {
              step++;
              if (step==3) {step=1};
              if (step==1) {document.title='【　　　　　】'+_title};
              if (step==2) {document.title='【您有新订单】'+_title};
            }, 500);

            return [timer, _title];
          },

          /**
           * @param timerArr[0], timer标记
           * @param timerArr[1], 初始的title文本内容
           */
          clear : function(timerArr) {    //去除闪烁提示，恢复初始title文本
            if(timerArr) {
              clearInterval(timerArr[0]);
              document.title = timerArr[1];
            };
          }
        }
      });

      var timerArr;

      window.setInterval(function () {
        var newOrderCount = $("#newOrder ul").children().length;
        var dispatchingOrderCount = $("#dispatchingOrder ul").children().length;
        $.getJSON("${pageContext.request.contextPath}/api/order/newOrder",
                {"count": newOrderCount},
                function (data) {
                  if(data) {
                    $("#newOrder ul").append(
                            "<li>id: " + data.id
                            + "  total price: ￥" + data.totalPrice/100
                            + "  time:" + new Date().getTime()
                            + "<a id='" + data.id + "'onclick='dispatchOrder()'></a>"
                            + "</li>"
                    );
                    timerArr = $.blinkTitle.show();  //title blink
                  }
                  else $.blinkTitle.clear(timerArr);   //title become normal
                });
        $.getJSON("${pageContext.request.contextPath}/api/order/dispatchingOrder",
                {"count": dispatchingOrderCount},
                function (data) {
                  if(data) {
                    $("#dispatchingOrder ul").append(
                            "<li>id: " + data.id
                            + "  total price: ￥" + data.totalPrice/100
                            + "  time:" + new Date().getTime() + "</li>"
                    );
                  }
                });
      }, 3000);

    });
  </script>
</body>
</html>
