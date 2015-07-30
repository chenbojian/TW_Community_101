<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>管理商品分类</title>
  <link rel="stylesheet" href="${pageContext.request.contextPath}/js/components/bootstrap/dist/css/bootstrap.min.css"/>
</head>
<body>
<jsp:include page="./template/navbar.jsp"></jsp:include>
<div class="container">
  <h3>分类管理</h3>
  <table class="table">
    <tr>
      <th class="col-sm-8">分类名称</th>
      <th>操作</th>
    </tr>
    <c:forEach var="category" items="${categories}">
      <tr>
        <td>
            ${category.name}
        </td>
        <td>
          <button class="btn btn-default" value="${category.id}">修改</button>
          <a class="btn btn-default"
             href="${pageContext.request.contextPath}/manage/category/${category.id}/delete">删除</a>
        </td>
      </tr>
    </c:forEach>
  </table>

  <div class="col-sm-8">
    <form class="form-horizontal" action="${pageContext.request.contextPath}/manage/category/add" method="post">
      <div class="col-sm-6">
        <div class="form-group">
          <input class="form-control" type="text" name="categoryName"/>
        </div>
      </div>
      <div class="col-sm-6">
        <button class="btn btn-primary">添加分类</button>
        <a class="btn btn-default" href="${pageContext.request.contextPath}/">返回</a>
      </div>
    </form>
  </div>
</div>
<script src="${pageContext.request.contextPath}/js/components/jquery/dist/jquery.min.js"></script>
<script src="${pageContext.request.contextPath}/js/components/bootstrap/dist/js/bootstrap.min.js"></script>
<script src="${pageContext.request.contextPath}/js/components/underscore/underscore-min.js"></script>
<script type="text/html" id="editCategory">
  <form action="${pageContext.request.contextPath}/manage/category/<\%=id%>/edit" method="post">
    <div class="form-group col-sm-10">
      <input class="form-control" type="text" name="categoryName" value="<\%=editObj.html().trim()%>"/>
    </div>
    <button class="btn btn-default">完成</button>
  </form>
</script>

<script>
  $(function () {
    $("table button").click(function () {
      var editObj = $(this).parent().prev();
      if ($(this).html() == "修改") {
        var template = _.template($("#editCategory").html());
        var html = template({id: $(this).id, editObj: editObj});
        editObj.html(html);
        $(this).html("取消");
      } else {
        editObj.html($("[name='categoryName']").val());
        $(this).html("修改");
      }
    });
  });

</script>
</body>
</html>
