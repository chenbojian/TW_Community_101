<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: chenbojian
  Date: 7/17/15
  Time: 10:28 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>input goods page</title>
  <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.min.css"/>
</head>
<body>
<jsp:include page="./template/navbar.jsp"></jsp:include>
<div class="container">
  <p>${message}</p>
  <form:form cssClass="form-horizontal"
             modelAttribute="inputGoodsDTO" method="post" enctype="multipart/form-data">
    <div class="form-group">
      <label class="col-sm-1 control-label">商品名称:</label>

      <div class="col-sm-10">
        <form:input cssClass="form-control" path="name"/>
      </div>
    </div>
    <div class="form-group">
      <label class="col-sm-1 control-label">商品类型:</label>

      <div class="col-sm-10">
        <select class="form-control" name="categoryId" id="categoryId">
          <c:forEach var="category" items="${categories}">
            <option value="${category.id}">${category.name}</option>
          </c:forEach>
        </select>
      </div>
    </div>
    <div class="form-group">
      <label class="col-sm-1 control-label">商品详情:</label>

      <div class="col-sm-10">
        <form:textarea cssClass="form-control" path="description"/>
      </div>
    </div>
    <div class="form-group">
      <label class="col-sm-1 control-label">商品价格:</label>

      <div class="col-sm-10">
        <form:input path="price" cssClass="form-control"/>
      </div>
    </div>
    <div class="form-group">
      <label class="col-sm-1 control-label">商品图片:</label>

      <div class="col-sm-10">
        <input type="file" name="pictureFile"/>
      </div>
    </div>
    <div class="form-group">
      <div class="col-sm-offset-1 col-sm-10">
        <input type="submit" class="btn btn-default" value="提交"/>
      </div>
    </div>
  </form:form>
</div>
<script src="${pageContext.request.contextPath}/js/jquery.min.js"></script>
<script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
</body>
</html>
