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
<div class="container">
  <form class="form-horizontal">
    <div class="form-group">
      <label class="col-sm-1 control-label">商品名称:</label>

      <div class="col-sm-10">
        <input type="text" class="form-control" title=""/>
      </div>
    </div>
    <div class="form-group">
      <label class="col-sm-1 control-label">商品类型:</label>

      <div class="col-sm-10">
        <select class="form-control" name="category.id" title="">
          <option>1</option>
        </select>
      </div>
    </div>
    <div class="form-group">
      <label class="col-sm-1 control-label">商品详情:</label>

      <div class="col-sm-10">
        <textarea class="form-control" rows="3" title=""></textarea>
      </div>
    </div>
    <div class="form-group">
      <label class="col-sm-1 control-label">商品价格:</label>

      <div class="col-sm-10">
        <input type="text" class="form-control" title=""/>
      </div>
    </div>

    <div class="form-group" ng-show="! isUseLocalPicture">
      <label class="col-sm-1 control-label">商品图片:</label>

      <div class="col-sm-8">
        <input type="text" class="form-control" title=""/>
      </div>
      <div class="col-sm-2">
        <button class="btn btn-default" type="button" ng-click="useLocalPicture()">
          使用本地图片
        </button>
      </div>
    </div>

    <div class="form-group" ng-show="isUseLocalPicture">
      <label class="col-sm-1 control-label">商品图片:</label>

      <div class="col-sm-8">
        <input type="file" nv-file-select="" uploader="uploader"
               ng-disabled="uploader.queue.length"/>
      </div>
      <div class="col-sm-2">
        <button class="btn btn-default" type="button" ng-click="useRemotePicture()">
          使用远程图片
        </button>
      </div>
    </div>
    <div class="form-group" ng-show="uploader.queue.length">
      <div class=" col-sm-offset-1 col-sm-10">
        <table class="table">
          <tr>
            <th width="40%">Name</th>
            <th ng-show="uploader.isHTML5">Size</th>
            <th ng-show="uploader.isHTML5">Progress</th>
            <th>Status</th>
            <th>Actions</th>
          </tr>
          <tr ng-repeat="item in uploader.queue">
            <td><strong>{{ item.file.name }}</strong></td>
            <td ng-show="uploader.isHTML5" nowrap>{{ item.file.size/1024/1024|number:2 }} MB</td>
            <td ng-show="uploader.isHTML5">
              <div class="progress" style="margin-bottom: 0;">
                <div class="progress-bar" role="progressbar" ng-style="{ 'width': item.progress + '%' }"></div>
              </div>
            </td>
            <td class="text-center">
              <span ng-show="item.isSuccess"><i class="glyphicon glyphicon-ok"></i></span>
              <span ng-show="item.isCancel"><i class="glyphicon glyphicon-ban-circle"></i></span>
              <span ng-show="item.isError"><i class="glyphicon glyphicon-remove"></i></span>
            </td>
            <td nowrap>
              <button type="button" class="btn btn-success btn-xs" ng-click="item.upload()"
                      ng-disabled="item.isReady || item.isUploading || item.isSuccess">
                <span class="glyphicon glyphicon-upload"></span> Upload
              </button>
              <button type="button" class="btn btn-warning btn-xs" ng-click="item.cancel()"
                      ng-disabled="!item.isUploading">
                <span class="glyphicon glyphicon-ban-circle"></span> Cancel
              </button>
              <button type="button" class="btn btn-danger btn-xs" ng-click="item.remove()">
                <span class="glyphicon glyphicon-trash"></span> Remove
              </button>
            </td>
          </tr>
        </table>
      </div>
    </div>

    <div class="form-group">
      <label class="col-sm-1 control-label">商品状态:</label>

      <div class="col-sm-10">
        <select name="status" title="">
          <option value="selling">售卖中</option>
          <option value="not selling">下架</option>
        </select>
      </div>
    </div>

    <div class="form-group">
      <div class="col-sm-offset-1 col-sm-10">
        <input type="submit" class="btn btn-primary" value="提交"/>
      </div>
    </div>
  </form>
</div>
