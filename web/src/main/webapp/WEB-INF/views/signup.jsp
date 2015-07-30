<%--
  Created by IntelliJ IDEA.
  User: MiffyLiye
  Date: 29/07/2015
  Time: 17:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <title>注册</title>
  <script src="<%=request.getContextPath()%>/js/angular/angular.min.js"></script>
  <script src="<%=request.getContextPath()%>/js/env.js"></script>
  <script src="<%=request.getContextPath()%>/js/angular/sign-in-controller.js"></script>
  <link href="<%=request.getContextPath()%>/css/bootstrap.min.css" rel="stylesheet"/>
  <link href="<%=request.getContextPath()%>/css/login.css" rel="stylesheet"/>
</head>
<body ng-app="App" ng-controller="signInController">
<jsp:include page="template/customerNavbar.jsp"></jsp:include>

<div class="row">
  <div class="center">
    <div class="well well-padding">
      <legend>注册</legend>
      <form method="POST" action="<%=request.getContextPath()%>/customer/signIn" accept-charset="UTF-8">
        <div class="form-group">
          <input placeholder="联系电话" ng-model="tel_phone" type="text" name="telPhone">
        </div>
        <div class="form-group">
          <input placeholder="登录密码" ng-model="password" type="password" name="password">
        </div>
        <div class="form-group">
          <input placeholder="重复密码" ng-model="password_repeat" type="password">
          <small>{{password_msg}}</small>
        </div>
        <div class="form-group">
          <input placeholder="短信验证码" ng-model="SMS_code" type="text" name="SMS_code">
          <button ng-click="send_SMS_code()" id="send_SMS_code" class="btn margin-left-10">{{send_msg_btn}}</button>
        </div>

        <button class="btn-info btn" type="submit">注册</button>
        <a href="<%=request.getContextPath()%>/customer/login/">已经有账号了？去登录</a>
      </form>
    </div>
  </div>
</div>


</body>
</html>
