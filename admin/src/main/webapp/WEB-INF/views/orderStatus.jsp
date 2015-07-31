<%--
  Created by IntelliJ IDEA.
  User: chenjian
  Date: 7/30/15
  Time: 11:05 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="container">
<h1>总的订单数：{{orders_total_num}}总的收益：{{orders_total_price/100 | currency : "¥" : 2}}</h1>

<h2>新订单</h2>

<h2>数量：{{new_num}} 收益：{{new_total_price/100 | currency : "¥" : 2}}</h2>

<div ng-repeat="item in new_list">
  <div>订单号：{{item["orderId"]}}
    下单时间：{{item["createTime"] | date:'yyyy-MM-dd HH:mm:ss'}}
    联系方式：{{item["telPhone"]}}
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
        <td> {{goods.price/100 | currency : "¥" : 2}}</td>
        <td> {{goods.quantity}}</td>
      </tr>
    </table>
  </div>
  <div>订单总价：{{item["totalPrice"]/100 | currency : "¥" : 2}}</div>
  <br/>
</div>
<br/><br/>

<h2>配送中订单</h2>

<h2>数量：{{dispatching_num}} 收益：{{dispatching_total_price/100 | currency : "¥" : 2}}</h2>

<div ng-repeat="item in dispatching_list">
  <div>订单号：{{item["orderId"]}}
    下单时间：{{item["createTime"] | date:'yyyy-MM-dd HH:mm:ss'}}
    联系方式：{{item["telPhone"]}}
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
        <td> {{goods.price/100 | currency : "¥" : 2}}</td>
        <td> {{goods.quantity}}</td>
      </tr>
    </table>
  </div>
  <div>订单总价：{{item["totalPrice"]/100 | currency : "¥" : 2}}</div>
  <br/>
</div>
<br/><br/>

<h2>已完成订单</h2>

<h2>数量：{{completed_num}} 收益：{{completed_total_price/100 | currency : "¥" : 2}}</h2>

<div ng-repeat="item in completed_list">
  <div>订单号：{{item["orderId"]}}
    下单时间：{{item["createTime"] | date:'yyyy-MM-dd HH:mm:ss'}}
    联系方式：{{item["telPhone"]}}
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
        <td> {{goods.price/100 | currency : "¥" : 2}}</td>
        <td> {{goods.quantity}}</td>
      </tr>
    </table>
  </div>
  <div>订单总价：{{item["totalPrice"]/100 | currency : "¥" : 2}}</div>
  <br/>
</div>
</div>