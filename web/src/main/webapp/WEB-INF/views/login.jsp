<%--
  Created by IntelliJ IDEA.
  User: MiffyLiye
  Date: 29/07/2015
  Time: 17:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="zh-Hans">
<head>
  <meta charset="UTF-8">
  <title>登录</title>
  <script src="<%=request.getContextPath()%>/js/angular/angular.min.js"></script>
  <script src="<%=request.getContextPath()%>/js/angular/login-controller.js"></script>
  <link href="<%=request.getContextPath()%>/css/bootstrap.min.css" rel="stylesheet"/>
  <link href="<%=request.getContextPath()%>/css/login.css" rel="stylesheet"/>
</head>
<body ng-app="App" ng-controller="loginController">
<jsp:include page="template/customerNavbar.jsp"></jsp:include>

<div class="row">
  <div class="center">
    <div class="well well-padding">
      <legend>注册</legend>
      <form method="POST" action="<%=request.getContextPath()%>/customer/login" accept-charset="UTF-8">
        <div class="form-group">
          <input placeholder="联系电话" ng-model="tel_phone" type="text" name="telPhone">
        </div>
        <div class="form-group">
          <input placeholder="登录密码" ng-model="password" type="password" name="password">
        </div>
        <button class="btn-info btn" type="submit">登录</button>
        <a href="<%=request.getContextPath()%>/customer/signup/">还没有账号？去注册</a>
      </form>
    </div>
  </div>
</div>

</body>
</html>
