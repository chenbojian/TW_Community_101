<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
  </ul>

  <div id="content">
    <div id="newOrder">
      new order:
      <ul>
        <%--<c:forEach var="orders" items="${newOrdersList}">--%>
          <%--<li>id:${orders.id} total price:￥${orders.totalPrice/100}</li>--%>
        <%--</c:forEach>--%>
      </ul>
    </div>
    <div id="dispatchingOrder">
      dispatching order:
      <ul>
        <c:forEach var="orders" items="${dispatchingOrdersList}">
          <li>id:${orders.id} total price:￥${orders.totalPrice/100} <button id="${orders.id}" onclick="completeOrder(this)">complete</button></li>
        </c:forEach>
      </ul>
    </div>
    <div id="completedOrder">
      completed order:
      <ul>
        <c:forEach var="orders" items="${completedOrdersList}">
          <li>id:${orders.id} total price:￥${orders.totalPrice/100}</li>
        </c:forEach>
      </ul>
    </div>

  <script src="${pageContext.request.contextPath}/lib/js/jquery-2.1.4.min.js"></script>
  <script type="text/javascript">
    function dispatchOrder(obj){
      var orderId = $(obj).attr("id");
      $.get("${pageContext.request.contextPath}/api/order/dispatchOrder"
              ,{"orderId":orderId}
              ,function(){}
      );
      obj.closest("li").remove();
      $.getJSON("${pageContext.request.contextPath}/api/order/getOrder",
              {"orderId": orderId},
              function (data) {
                if(data) {
                  $("#dispatchingOrder ul").append(
                          "<li>id: " + data.id
                          + "  total price: ￥" + data.totalPrice/100
                          + "<button id='" + data.id + "'onclick='completeOrder(this)'>complete</button>"
                          + "</li>"
                  );
                }
              });
    }

    function completeOrder(obj){
      var orderId = $(obj).attr("id");
      $.get("${pageContext.request.contextPath}/api/order/completeOrder"
              ,{"orderId":orderId}
              ,function(){}
      );
      obj.closest("li").remove();
      $.getJSON("${pageContext.request.contextPath}/api/order/getOrder",
              {"orderId": orderId},
              function (data) {
                if(data) {
                  $("#completedOrder ul").append(
                          "<li>id: " + data.id
                          + "  total price: ￥" + data.totalPrice/100
                          + "</li>"
                  );
                }
              });
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
        $.getJSON("${pageContext.request.contextPath}/api/order/newOrder",
                {"count": newOrderCount},
                function (data) {
                  if(data) {
                    $("#newOrder ul").append(
                            "<li>id: " + data.id
                            + "  total price: ￥" + data.totalPrice/100
                            + "<button id='" + data.id + "'onclick='dispatchOrder(this)'>dispatch order</button>"
                            + "</li>"
                    );
                    timerArr = $.blinkTitle.show();  //title blink
                    setTimeout(function() {     // title become normal 5s later
                      $.blinkTitle.clear(timerArr);
                    }, 4999);
                  }
                });
      }, 5000);

    });
  </script>

  <embed src="${pageContext.request.contextPath}/sounds/msg.wav" autostart=false height="0" width="0" loop=false id="sound"></embed>
</body>
</html>
