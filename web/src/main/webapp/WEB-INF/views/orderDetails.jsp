<%--
  Created by IntelliJ IDEA.
  User: MiffyLiye
  Date: 29/07/2015
  Time: 16:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html ng-app="App">
<head lang="en">
  <meta http-equiv="CONTENT-TYPE" CONTENT="text/html" charset="UTF-8">
  <title>Order Detail | Community 101</title>
  <link href="<%=request.getContextPath()%>/css/bootstrap.min.css" rel="stylesheet"/>
  <link href="<%=request.getContextPath()%>/css/index.css" rel="stylesheet"/>
  <link rel="stylesheet" href="<%=request.getContextPath()%>/css/order.css" type="text/css">

  <script src="<%=request.getContextPath()%>/js/jquery.min.js"></script>
  <script src="<%=request.getContextPath()%>/js/bootstrap.min.js"></script>
  <script src="<%=request.getContextPath()%>/js/angular/angular.min.js"></script>
  <script src="<%=request.getContextPath()%>/js/angular/angular-cookies.min.js"></script>
  <script src="<%=request.getContextPath()%>/js/angular/order_detail_controller.js"></script>
</head>
<body ng-controller="OrderDetailController">
<jsp:include page="template/customerNavbar.jsp"></jsp:include>

<p>订单号：{{item["orderId"]}}</p>
<p>订单状态：{{order_status}}</p>
<p>下单时间：{{item["createTime"] | date:'yyyy-MM-dd HH:mm:ss'}}</p>
<p>联系方式：{{item["telPhone"]}}</p>
<p>配送地址：{{item["address"]}}</p>
<p>选购商品：</p>
<table class="table">
  <tr>
    <th colspan="2">商品信息</th>
    <th>单价</th>
    <th>数量</th>
    <th>金额</th>
  </tr>
  <tr ng-repeat="goods in item.goodsInOrderDTOList">
    <td><img class="goodsLogoInCart" src={{goods.pic}} alt={{goods.name}}"/></td>
    <td><a href="javascript:void(0);" ng-click="getGoodsDetail()">{{goods.name}}</a></td>
    <td>{{goods.price/100 | currency : "¥" : 2}}</td>
    <td>{{goods.quantity}}</td>
    <td>{{goods.price/100 * goods.quantity | currency : "¥" : 2}}</td>
  </tr>
</table>
<div class="text-right">
  总价:<strong>{{item.totalPrice/100 | currency : "¥" : 2}}</strong>
</div>

<div id="goodsDetailTemplate"></div>
<script>
  $( "#goodsDetailTemplate" ).load( "<%=request.getContextPath()%>/html/goodsDetail.html" );
</script>

</body>
</html>
