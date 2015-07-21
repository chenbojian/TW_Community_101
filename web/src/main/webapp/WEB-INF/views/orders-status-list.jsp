<%--
  Created by IntelliJ IDEA.
  User: jiaoming
  Date: 7/17/15
  Time: 11:59 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>orders-status-list</title>
</head>
<body>

<br/>新订单：<br/>
 <c:forEach var="orders" items="${newOrdersList}">
  <div>
    客户联系方式：
    ${orders.user.telPhone}
  </div>
  <div>
    客户地址：
    ${orders.address}
  </div>
  <div>
    订单内容：
    <c:forEach var="orderGoodses" items="${orders.orderGoodses}">
    <div>
      &nbsp;&nbsp;商品名称：${orderGoodses.goodsName}；
      &nbsp;&nbsp;商品数量：${orderGoodses.count}；
      &nbsp;&nbsp;商品单价：${orderGoodses.goodsPrice}
    </div>
    </c:forEach>
  </div>
  <div>
    消费总价：
    ${orders.totalPrice}
  </div>
</c:forEach>

<br/>配送中订单：<br/>
<c:forEach var="orders" items="${dispatchingOrdersList}">
  <div>
    客户联系方式：
      ${orders.user.telPhone}
  </div>
  <div>
    客户地址：
      ${orders.address}
  </div>
  <div>
    订单内容：
    <c:forEach var="orderGoodses" items="${orders.orderGoodses}">
      <div>
        &nbsp;&nbsp;商品名称：${orderGoodses.goodsName}；
        &nbsp;&nbsp;商品数量：${orderGoodses.count}；
        &nbsp;&nbsp;商品单价：${orderGoodses.goodsPrice}
      </div>
    </c:forEach>
  </div>
  <div>
    消费总价：
      ${orders.totalPrice}
  </div>
</c:forEach>

<br/>完成订单：<br/>
<c:forEach var="orders" items="${completedOrdersList}">
  <div>
    客户联系方式：
      ${orders.user.telPhone}
  </div>
  <div>
    客户地址：
      ${orders.address}
  </div>
  <div>
    订单内容：
    <c:forEach var="orderGoodses" items="${orders.orderGoodses}">
      <div>
        &nbsp;&nbsp;商品名称：${orderGoodses.goodsName}；
        &nbsp;&nbsp;商品数量：${orderGoodses.count}；
        &nbsp;&nbsp;商品单价：${orderGoodses.goodsPrice}
      </div>
    </c:forEach>
  </div>
  <div>
    消费总价：
      ${orders.totalPrice}
  </div>
</c:forEach>

</body>
</html>
