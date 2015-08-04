<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<link rel="stylesheet" href="<%=request.getContextPath()%>/css/order.css" type="text/css">

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
      <td><img class="goodsLogoInCart" ng-src={{goods.pic}} alt={{goods.name}}></td>
      <td><a href="javascript:void(0);" ng-click="getOrderGoodsDetail()">{{goods.name}}</a></td>
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

<jsp:include page="template/goodsDetails.jsp"></jsp:include>
