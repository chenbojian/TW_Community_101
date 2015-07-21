<%--
  Created by IntelliJ IDEA.
  User: jiaoming
  Date: 7/17/15
  Time: 4:09 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Show Customer Orders Status</title>
</head>
<body>
<div items="${orders}">
  订单号：${orders.id}
</div>
<div>
  订单状态：
  <c:choose>
    <c:when test="${orders.status=='new'}">
      等待店家接单
    </c:when>
    <c:when test="${orders.status=='dispatching'}">
      配送中。。。
    </c:when>
    <c:when test="${orders.status=='completed'}">
      订单已完成
    </c:when>
    <c:when test="${orders.status=='cancel'}">
      订单已取消
    </c:when>
  </c:choose>
</div>
<div>
  下单时间：
  ${orders.createTime}
</div>
<div>
  联系方式：
  ${orders.user.telPhone}
</div>
<div>
  配送地址：
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
<%--<div>--%>
  <%--<label>商品图片 :</label>--%>
  <%--<input id="pic" type="file" name="pic"/>--%>
<%--</div>--%>
</body>
</html>
