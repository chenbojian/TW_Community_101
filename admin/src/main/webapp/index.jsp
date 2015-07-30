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
  <link rel="stylesheet" href="js/components/bootstrap/dist/css/bootstrap.min.css"/>
</head>
<body ng-app="adminApp">
<jsp:include page="/navbar"></jsp:include>

<div ng-view></div>

<script src="js/components/jquery/dist/jquery.min.js"></script>
<script src="js/components/bootstrap/dist/js/bootstrap.min.js"></script>
<script src="js/components/angular/angular.min.js"></script>
<script src="js/components/angular-cookie/angular-cookie.min.js"></script>
<script src="js/components/angular-route/angular-route.min.js"></script>
<script src="js/app/admin.js"></script>
<script src="js/app/controller/categoryController.js"></script>
</body>
</html>
