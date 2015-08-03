<%@ page contentType="text/html;charset=UTF-8" language="java" %>
  <link href="<%=request.getContextPath()%>/css/login.css" rel="stylesheet"/>

<div class="row">
  <div class="center">
    <div class="well well-padding">
      <legend>登录</legend>
      <form method="POST" action="<%=request.getContextPath()%>/customer/login" accept-charset="UTF-8">
        <div class="form-group">
          <input placeholder="联系电话" ng-model="tel_phone" type="text" name="telPhone">
        </div>
        <div class="form-group">
          <input placeholder="登录密码" ng-model="password" type="password" name="password">
        </div>
        <button class="btn-info btn" type="submit">登录</button>
        <a href="#/signup">还没有账号？去注册</a>
      </form>
    </div>
  </div>
</div>
