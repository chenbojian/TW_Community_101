<%--
  Created by IntelliJ IDEA.
  User: chenbojian
  Date: 7/27/15
  Time: 5:12 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<nav class="navbar navbar-default">
  <div class="container-fluid">
    <div class="navbar-header">
      <button type="button" class="navbar-toggle collapsed" data-toggle="collapse"
              data-target="#navbar-collapse" aria-expanded="false">
        <span class="sr-only">Toggle navigation</span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
      </button>
      <a class="navbar-brand" ng-href="#/">Community101</a>
    </div>

    <div class="collapse navbar-collapse" id="navbar-collapse">
      <ul class="nav navbar-nav">
        <li><a ng-href="#/order-manager">订单管理</a></li>
        <li class="dropdown">
          <a class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true"
             aria-expanded="false">商品管理<span class="caret"></span></a>
          <ul class="dropdown-menu">
            <li><a ng-href="#/goods/input">添加商品</a></li>
            <li><a ng-href="#/category/manage">类型管理</a></li>
            <li><a ng-href="#">库存管理</a></li>
            <li><a ng-href="#">打折活动</a></li>
          </ul>
        </li>
        <li class="dropdown">
          <a class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true"
             aria-expanded="false">报表管理<span class="caret"></span></a>
          <ul class="dropdown-menu">
            <li><a ng-href="#/order-status">订单汇总</a></li>
            <li><a>时间报表</a></li>
            <li><a>商品报表</a></li>
          </ul>
        </li>
      </ul>
    </div>

  </div>
</nav>
