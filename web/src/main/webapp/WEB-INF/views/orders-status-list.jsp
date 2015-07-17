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
<c:forEach var="orders" items="${orderses}">
  <tr>
    <td>${orders.id}</td>
    <td>${orders.name}</td>
    <td>${orders.address}</td>
  </tr>
</c:forEach>

</body>
</html>
