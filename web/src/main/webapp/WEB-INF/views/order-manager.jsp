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
  <div id="logs"></div>

  <script src="//cdn.bootcss.com/jquery/2.1.4/jquery.min.js"></script>
  <script type="text/javascript">
    $(function () {
      window.setInterval(function () {
        $.get("${pageContext.request.contextPath}/api/order/newOrder",
                {"timed": new Date().getTime()},
                function (data) {
                  if(data) {
                    $("#logs").append("[ new order: " + data + " ] time:" + new Date().getTime() + "<br/>");
                  }
                });
      }, 3000);

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

      var timerArr = $.blinkTitle.show();  //标题闪烁

      setTimeout(function() {     //10s后自动消失
        $.blinkTitle.clear(timerArr);
      }, 10000);

    });
  </script>
</body>
</html>
