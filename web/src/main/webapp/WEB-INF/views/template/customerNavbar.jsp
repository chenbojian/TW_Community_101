<%--
  Created by IntelliJ IDEA.
  User: MiffyLiye
  Date: 29/07/2015
  Time: 15:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<nav class="navbar navbar-default">
  <div class="container-fluid">
    <div class="navbar-header">
      <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
        <span class="sr-only">Toggle navigation</span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
      </button>
      <a class="navbar-brand" href="<%=request.getContextPath()%>/">Community101</a>
    </div>

    <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
      <ul class="nav navbar-nav">
        <li><a href="<%=request.getContextPath()%>/customer/order-history/">订单记录</a></li>
        <li><a href="<%=request.getContextPath()%>/customer/signup/">注册</a></li>
        <li><a href="<%=request.getContextPath()%>/customer/login/">登录</a></li>
        <li><a onclick="$functions.logout()" href="javascript:void(0);">登出</a></li>
      </ul>
    </div>
  </div>
</nav>

