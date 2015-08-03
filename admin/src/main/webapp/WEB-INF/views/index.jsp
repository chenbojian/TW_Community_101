<%--
  Created by IntelliJ IDEA.
  User: chenbojian
  Date: 7/30/15
  Time: 12:25 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>后台管理系统</title>
  <link rel="stylesheet" href="${pageContext.request.contextPath}/js/components/bootstrap/dist/css/bootstrap.min.css"/>
</head>
<body ng-app="adminApp">
<jsp:include page="/navbar"></jsp:include>

<div ng-view></div>

<script src="api/env/env.js"></script>
<script src="${pageContext.request.contextPath}/js/components/jquery/dist/jquery.min.js"></script>
<script src="${pageContext.request.contextPath}/js/components/bootstrap/dist/js/bootstrap.min.js"></script>
<script src="${pageContext.request.contextPath}/js/components/underscore/underscore-min.js"></script>
<script src="${pageContext.request.contextPath}/js/components/angular/angular.min.js"></script>
<script src="${pageContext.request.contextPath}/js/components/angular-cookie/angular-cookie.min.js"></script>
<script src="${pageContext.request.contextPath}/js/components/angular-route/angular-route.min.js"></script>
<script src="${pageContext.request.contextPath}/js/components/angular-resource/angular-resource.min.js"></script>
<script
    src="${pageContext.request.contextPath}/js/components/angular-file-upload/dist/angular-file-upload.min.js"></script>
<script src="${pageContext.request.contextPath}/js/app/admin.js"></script>
<script src="${pageContext.request.contextPath}/js/app/controller/categoryController.js"></script>
<script src="${pageContext.request.contextPath}/js/app/controller/order-manager-controller.js"></script>
<script src="${pageContext.request.contextPath}/js/app/controller/order-status-controller.js"></script>
<script src="${pageContext.request.contextPath}/js/app/controller/goodsController.js"></script>
</body>
</html>
