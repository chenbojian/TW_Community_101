<%--
  Created by IntelliJ IDEA.
  User: chenjian
  Date: 7/30/15
  Time: 11:00 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<h2>新订单</h2>

<div ng-repeat="order in newOrders">
  <hr class="order"/>
  <div>订单号：{{order.orderId}}<br/>
    下单时间：{{order.createTime | date:'yyyy-MM-dd HH:mm:ss'}}<br/>
    电话：{{order.telPhone}}<br/>
    地址：{{order.address}}
  </div>
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
      <td colspan="2">总价:<strong>{{order.totalPrice/100 | currency : "¥" : 2}}</strong></td>
      <td colspan="3">
        <button type="button" class="btn btn-success" ng-click="dispatchOrder(order.orderId)">派送</button>
      </td>
    </tr>
    </tfoot>
  </table>
  <hr class="order"/>
</div>

<h2>派送中订单</h2>

<div ng-repeat="order in dispatchingOrders">
  <hr class="order"/>
  <div>订单号：{{order.orderId}}<br/>
    下单时间：{{order.createTime | date:'yyyy-MM-dd HH:mm:ss'}}<br/>
    电话：{{order.telPhone}}<br/>
    地址：{{order.address}}
  </div>
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
      <td colspan="2">总价:<strong>{{order.totalPrice/100 | currency : "¥" : 2}}</strong></td>
      <td colspan="3">
        <button type="button" class="btn btn-success" ng-click="completeOrder(order.orderId)">完成</button>
      </td>
    </tr>
    </tfoot>
  </table>
  <hr class="order"/>
</div>

<h2>已完成订单</h2>

<div ng-repeat="order in completedOrders">
  <hr class="order"/>
  <div>订单号：{{order.orderId}}<br/>
    下单时间：{{order.createTime | date:'yyyy-MM-dd HH:mm:ss'}}<br/>
    电话：{{order.telPhone}}<br/>
    地址：{{order.address}}
  </div>
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
      <td colspan="2">总价:<strong>{{order.totalPrice/100 | currency : "¥" : 2}}</strong></td>
      <td colspan="3"></td>
    </tr>
    </tfoot>
  </table>
  <hr class="order"/>
</div>

<audio src="sounds/msg.wav" id="sound" preload="preload"></audio>

<div id="goodsDetailTemplate"></div>
<script>
  $("#goodsDetailTemplate").load("${pageContext.request.contextPath}/html/goodsDetail.html");
</script>
