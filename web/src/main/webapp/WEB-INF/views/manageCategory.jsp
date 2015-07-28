<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>管理商品分类</title>
  <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.min.css"/>
</head>
<body>
<jsp:include page="./template/navbar.jsp"></jsp:include>
<div class="container">
  <h3>分类管理</h3>
  <table class="table">
    <tr>
      <th>分类名称</th>
      <th>操作</th>
    </tr>
    <c:forEach var="category" items="${categories}">
      <tr>
        <td>${category.name}</td>
        <td>
          <a class="btn btn-default"
             href="${pageContext.request.contextPath}/manage/category/${category.id}/edit">修改</a>
          <a class="btn btn-default"
             href="${pageContext.request.contextPath}/manage/category/${category.id}/delete">删除</a>
        </td>
      </tr>
    </c:forEach>
  </table>
  <button class="btn btn-primary">添加</button>
  <a class="btn btn-default" href="${pageContext.request.contextPath}/">返回</a>
</div>
<script src="${pageContext.request.contextPath}/js/jquery.min.js"></script>
<script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
</body>
</html>
