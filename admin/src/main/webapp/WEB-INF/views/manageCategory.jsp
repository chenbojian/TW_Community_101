<%--
  Created by IntelliJ IDEA.
  User: chenbojian
  Date: 7/30/15
  Time: 5:32 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="container">
  <h3>分类管理</h3>
  <table class="table">
    <tr>
      <th class="col-sm-8">分类名称</th>
      <th>操作</th>
    </tr>
    <tr ng-repeat="category in categories">
      <td>
        {{category.name}}
      </td>
      <td>
        <button class="btn btn-default">修改</button>
        <a class="btn btn-default" ng-click="deleteCategory(category.id)">删除</a>
      </td>
    </tr>
  </table>

  <div class="col-sm-8">
    <form class="form-horizontal" ng-submit="createCategory()">
      <div class="col-sm-6">
        <div class="form-group">
          <input class="form-control" type="text" name="categoryName" ng-model="category.name"/>
        </div>
      </div>
      <div class="col-sm-6">
        <button class="btn btn-primary" type="submit">添加分类</button>
        <a class="btn btn-default" href="${pageContext.request.contextPath}/">返回</a>
      </div>
    </form>
  </div>
</div>
