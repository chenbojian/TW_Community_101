<%--
  Created by IntelliJ IDEA.
  User: MiffyLiye
  Date: 29/07/2015
  Time: 18:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="zh-Hans">
<head>
  <meta charset="UTF-8">
  <title>社区101</title>
  <link href="<%=request.getContextPath()%>/css/bootstrap.min.css" rel="stylesheet" />
  <link href="<%=request.getContextPath()%>/css/index.css" rel="stylesheet" />
  <script src="<%=request.getContextPath()%>/js/jquery.min.js"></script>
  <script src="<%=request.getContextPath()%>/js/bootstrap.min.js"></script>
  <script src="<%=request.getContextPath()%>/js/jquery.cookie.min.js"></script>
  <script src="<%=request.getContextPath()%>/js/index.js"></script>
</head>
<body>
<jsp:include page="template/customerNavbar.jsp"></jsp:include>

<div class="container-fluid">
  <div class="row">
    <div class="col-md-12">
      <div class="jumbotron">
        <h1 class="text-center">
          <kbd>社区101</kbd>
        </h1>
      </div>
    </div>
  </div>
  <div class="row">
    <div class="col-md-3">
      <a class="btn btn-success btn-block buybtn" href="<%=request.getContextPath()%>/customer/shopping-cart/">购买</a>
      <div class="list-group" id="categorylist">
        <div data-cid="0" class="list-group-item active">所有商品</div>
      </div>
    </div>
    <div class="col-md-9">
      <div class="row" id="goodslist"></div>
    </div>
  </div>
</div>

<div id="goodsDetailTemplate"></div>
<script>
  $( "#goodsDetailTemplate" ).load( "<%=request.getContextPath()%>/html/goodsDetail.html" );
</script>

</body>
</html>
