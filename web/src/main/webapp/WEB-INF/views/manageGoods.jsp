<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: chenbojian
  Date: 7/28/15
  Time: 11:45 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>管理商品</title>
  <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.min.css"/>
</head>
<body>
<jsp:include page="./template/navbar.jsp"></jsp:include>
<div class="container">
  <h3>商品管理</h3>

  <div class="col-sm-2">
    <div class="list-group">
      <a class="list-group-item">全部商品</a>
      <c:forEach var="category" items="${categories}">
        <a class="list-group-item">${category.name}</a>
      </c:forEach>
    </div>
  </div>
  <div class="col-sm-10">

  </div>
</div>
<script src="${pageContext.request.contextPath}/js/jquery.min.js"></script>
<script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
</body>
</html>
