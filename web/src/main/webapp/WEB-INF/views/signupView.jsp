<%@ page contentType="text/html;charset=UTF-8" language="java" %>
  <link href="<%=request.getContextPath()%>/css/login.css" rel="stylesheet"/>
<div class="row">
  <div class="center">
    <div class="well well-padding">
      <legend>注册</legend>
      <form method="POST" action="<%=request.getContextPath()%>/customer/signup" accept-charset="UTF-8">
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
        <a href="#/login">已经有账号了？去登录</a>
      </form>
    </div>
  </div>
</div>
