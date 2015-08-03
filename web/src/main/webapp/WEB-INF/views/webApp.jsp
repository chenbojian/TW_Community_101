<%--
  Created by IntelliJ IDEA.
  User: MiffyLiye
  Date: 03/08/2015
  Time: 10:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="zh-Hans">
<head>
    <title>社区101</title>
    <link href="<%=request.getContextPath()%>/css/bootstrap.min.css" rel="stylesheet" />
</head>
<body ng-App="webApp">

<jsp:include page="template/customerNavbar.jsp"></jsp:include>

<div ng-view></div>

<script src="<%=request.getContextPath()%>/api/env/env.js"></script>
<script src="<%=request.getContextPath()%>/js/jquery.min.js"></script>
<script src="<%=request.getContextPath()%>/js/bootstrap.min.js"></script>
<script src="<%=request.getContextPath()%>/js/default.js"></script>

<script src="<%=request.getContextPath()%>/js/angular/angular.min.js"></script>
<script src="<%=request.getContextPath()%>/js/angular/angular-route.min.js"></script>
<script src="<%=request.getContextPath()%>/js/angular/angular-resource.min.js"></script>
<script src="<%=request.getContextPath()%>/js/angular/angular-cookies.min.js"></script>

<script src="<%=request.getContextPath()%>/js/app/webApp.js"></script>
<script src="<%=request.getContextPath()%>/js/app/service/goods_service.js"></script>
<script src="<%=request.getContextPath()%>/js/app/controller/index_controller.js"></script>
<script src="<%=request.getContextPath()%>/js/app/controller/cart_controller.js"></script>
<script src="<%=request.getContextPath()%>/js/app/controller/order_details_controller.js"></script>
<script src="<%=request.getContextPath()%>/js/app/controller/order_history_controller.js"></script>
<script src="<%=request.getContextPath()%>/js/app/controller/sign-up-controller.js"></script>
</body>
</html>
