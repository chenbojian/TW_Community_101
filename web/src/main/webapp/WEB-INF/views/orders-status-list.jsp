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
新订单：
<c:forEach var="orders" items="${newOrdersList}">
  <tr>
    <td>${orders.id}</td>
    <td>${orders.status}</td>
    <td>${orders.address}</td>
  </tr>
</c:forEach>
配送中订单：
<c:forEach var="orders" items="${dispatchingOrdersList}">
  <tr>
    <td>${orders.id}</td>
    <td>${orders.status}</td>
    <td>${orders.address}</td>
  </tr>
</c:forEach>
完成订单：
<c:forEach var="orders" items="${completedOrdersList}">
  <tr>
    <td>${orders.id}</td>
    <td>${orders.status}</td>
    <td>${orders.address}</td>
  </tr>
</c:forEach>
取消订单：
<c:forEach var="orders" items="${cancelOrdersList}">
  <tr>
    <td>${orders.id}</td>
    <td>${orders.status}</td>
    <td>${orders.address}</td>
  </tr>
</c:forEach>
</body>
</html>
