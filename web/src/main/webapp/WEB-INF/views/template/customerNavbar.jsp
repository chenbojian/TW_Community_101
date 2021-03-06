<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
      <a class="navbar-brand" href="#/demonstration">Community101</a>
    </div>

    <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
      <ul class="nav navbar-nav">
        <li><a href="#/order-history">订单记录</a></li>

        <c:if test="${cookie['username'] != null && cookie['username'].value != '' }">
          <li><a>欢迎${cookie['username'].value}</a></li>
        <li><a href="javascript:document.logout.submit()">登出</a></li>
        </c:if>

        <c:if test="${cookie['username'] == null || cookie['username'].value == ''}">
        <li><a href="#/signup">注册</a></li>
        <li><a href="#/login">登录</a></li>
        </c:if>
        <form name="logout" method="POST" action="<%=request.getContextPath()%>/customer/logout/">
        </form>
      </ul>
    </div>
  </div>
</nav>

