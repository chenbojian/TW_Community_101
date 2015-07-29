<%--
  Created by IntelliJ IDEA.
  User: MiffyLiye
  Date: 29/07/2015
  Time: 15:26
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en" ng-app="App">
<head>
  <meta charset="UTF-8">
  <title>订单记录</title>
  <link href="/admin/css/bootstrap.min.css" rel="stylesheet"/>
  <link href="/admin/css/index.css" rel="stylesheet"/>
  <link rel="stylesheet" href="/admin/css/order.css" type="text/css">

  <script src="/admin/js/jquery.min.js"></script>
  <script src="/admin/js/bootstrap.min.js"></script>
  <script src="/admin/js/angular/angular.min.js"></script>
  <script src="/admin/js/angular/angular-cookies.min.js"></script>
  <script src="/admin/js/angular/order_history_controller.js"></script>
</head>
<body ng-controller="OrderHistoryController">
<jsp:include page="template/customerNavbar.jsp"></jsp:include>

<h1>订单记录</h1>
{{message}}

<div ng-repeat="order in order_list">
  <div>订单号: {{order.orderId}}。 下单时间: {{order.createTime | date:'yyyy-MM-dd HH:mm:ss'}}</div>
  <table class="table">
    <thead>
    <tr>
      <th colspan="2">商品信息</th>
      <th>单价</th>
      <th>数量</th>
      <th>金额</th>
    </tr>
    </thead>
    <tbody>
    <tr ng-repeat="goods in order.goodsInOrderDTOList">
      <td><img class="goodsLogoInCart" src={{goods.pic}} alt={{goods.name}}></td>
      <td><a href="javascript:void(0);" ng-click="getGoodsDetail()">{{goods.name}}</a></td>
      <td>{{goods.price/100 | currency : "¥" : 2}}</td>
      <td>{{goods.quantity}}</td>
      <td>{{goods.price/100 * goods.quantity | currency : "¥" : 2}}</td>
    </tr>
    </tbody>
    <tfoot>
    <tr>
      <td colspan="3">总价:<strong>{{order.totalPrice/100 | currency : "¥" : 2}}</strong></td>
      <td>状态: {{order.status}}</td>
      <td><button class="btn btn-default" ng-click="launchOrderDetails()">查看详情</button></td>
    </tr>
    </tfoot>
  </table>
</div>

<div id="goodsDetailTemplate"></div>
<script>
  $( "#goodsDetailTemplate" ).load( "/admin/html/goodsDetail.html" );
</script>

</body>
</html>
