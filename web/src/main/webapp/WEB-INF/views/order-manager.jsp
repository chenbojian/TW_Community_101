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
  <script type="text/javascript" src="${pageContext.request.contextPath}/lib/js/jquery-2.1.4min.js"/>
  <script type="text/javascript">
    $(function () {
      window.setInterval(function () {
        $.get("${pageContext.request.contextPath}/api/order/newOrder",
                {"timed": new Date().getTime()},
                function (data) {
                  $("#logs").append("[new order: " + data + " ]<br/>");
                });
      }, 3000);

    });
  </script>
</head>
<body>
  <div id="#logs"></div>
</body>
</html>
