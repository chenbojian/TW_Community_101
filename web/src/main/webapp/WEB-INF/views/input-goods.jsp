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
</head>
<body>
<p>${message}</p>
<form:form modelAttribute="inputGoodsDTO" method="post" enctype="multipart/form-data">
    <div>
        <label for="name">商品名称 :</label>
        <form:input path="name"/>
    </div>
    <div>
        <label for="categoryId">商品类型 :</label>
        <select name="categoryId" id="categoryId">
            <c:forEach var="category" items="${categories}">
                <option value="${category.id}">${category.name}</option>
            </c:forEach>
        </select>
    </div>
    <div>
        <label for="description">商品详情 :</label>
        <form:textarea path="description"/>
    </div>
    <div>
        <label for="price">商品价格 :</label>
        <form:input path="price"/>
    </div>
    <div>
        <label for="pictureFile">商品图片 :</label>
        <input id="pictureFile" type="file" name="pictureFile"/>
    </div>
    <div>
        <input type="submit" value="提交"/>
    </div>
</form:form>
</body>
</html>
