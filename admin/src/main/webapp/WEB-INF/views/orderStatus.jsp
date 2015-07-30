<%--
  Created by IntelliJ IDEA.
  User: chenjian
  Date: 7/30/15
  Time: 11:05 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="zh-Hans" ng-app="App">
<head>
  <meta charset="UTF-8">
  <title>Order Status | Community 101</title>
  <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet"/>

  <script src="${pageContext.request.contextPath}/js/components/jquery.min.js"></script>
  <script src="${pageContext.request.contextPath}/js/components/bootstrap.min.js"></script>
  <script src="${pageContext.request.contextPath}/js/angular/angular.min.js"></script>
  <script src="${pageContext.request.contextPath}/js/angular/angular-cookies.min.js"></script>
  <script src="${pageContext.request.contextPath}/js/app/controller/order-manager-controller.js"></script>
</head>
<body ng-controller="orderManagerController">

<jsp:include page="./template/navbar.jsp"></jsp:include>

<h1>总的订单数：{{orders_total_num}}&nbsp;&nbsp;&nbsp;&nbsp;总的收益：{{orders_total_price/100 | currency}}</h1>

<h2>新订单</h2>
<h2>数量：{{new_num}} &nbsp;&nbsp;&nbsp;&nbsp;收益：{{new_total_price/100 | currency}}</h2>
<div ng-repeat="item in new_list">
  <div>订单号：{{item["orderId"]}} &nbsp;&nbsp;&nbsp;&nbsp;
    下单时间：{{item["createTime"] | date:'yyyy-MM-dd HH:mm:ss'}}&nbsp;&nbsp;&nbsp;&nbsp;
    联系方式：{{item["telPhone"]}}&nbsp;&nbsp;&nbsp;&nbsp;
    配送地址：{{item["address"]}}
  </div>
  <div>选购商品：
    <table border="1" cellspacing="0">
      <tr>
        <th>商品名称</th>
        <th>商品单价</th>
        <th>选购数量</th>
      </tr>
      <tr ng-repeat="goods in item.goodsInOrderDTOList">
        <td> {{goods.name}}</td>
        <td> {{goods.price/100 | currency}}</td>
        <td> {{goods.quantity}}</td>
      </tr>
    </table>
  </div>
  <div>订单总价：{{item["totalPrice"]/100 | currency}}</div>
  <br/>
</div>
<br/><br/>
<h2>配送中订单</h2>
<h2>数量：{{dispatching_num}} &nbsp;&nbsp;&nbsp;&nbsp;收益：{{dispatching_total_price/100 | currency}}</h2>
<div ng-repeat="item in dispatching_list">
  <div>订单号：{{item["orderId"]}} &nbsp;&nbsp;&nbsp;&nbsp;
    下单时间：{{item["createTime"] | date:'yyyy-MM-dd HH:mm:ss'}}&nbsp;&nbsp;&nbsp;&nbsp;
    联系方式：{{item["telPhone"]}}&nbsp;&nbsp;&nbsp;&nbsp;
    配送地址：{{item["address"]}}
  </div>
  <div>选购商品：
    <table border="1" cellspacing="0">
      <tr>
        <th>商品名称</th>
        <th>商品单价</th>
        <th>选购数量</th>
      </tr>
      <tr ng-repeat="goods in item.goodsInOrderDTOList">
        <td> {{goods.name}}</td>
        <td> {{goods.price/100 | currency}}</td>
        <td> {{goods.quantity}}</td>
      </tr>
    </table>
  </div>
  <div>订单总价：{{item["totalPrice"]/100 | currency}}</div>
  <br/>
</div>
<br/><br/>
<h2>已完成订单</h2>
<h2>数量：{{completed_num}} &nbsp;&nbsp;&nbsp;&nbsp;收益：{{completed_total_price/100 | currency}}</h2>
<div ng-repeat="item in completed_list">
  <div>订单号：{{item["orderId"]}} &nbsp;&nbsp;&nbsp;&nbsp;
    下单时间：{{item["createTime"] | date:'yyyy-MM-dd HH:mm:ss'}}&nbsp;&nbsp;&nbsp;&nbsp;
    联系方式：{{item["telPhone"]}}&nbsp;&nbsp;&nbsp;&nbsp;
    配送地址：{{item["address"]}}
  </div>
  <div>选购商品：
    <table border="1" cellspacing="0">
      <tr>
        <th>商品名称</th>
        <th>商品单价</th>
        <th>选购数量</th>
      </tr>
      <tr ng-repeat="goods in item.goodsInOrderDTOList">
        <td> {{goods.name}}</td>
        <td> {{goods.price/100 | currency}}</td>
        <td> {{goods.quantity}}</td>
      </tr>
    </table>
  </div>
  <div>订单总价：{{item["totalPrice"]/100 | currency}}</div>
  <br/>
</div>
</body>
</html>
