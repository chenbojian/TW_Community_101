<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
      <a class="btn btn-success btn-block buybtn" href="#/shopping-cart">购买</a>
      <div class="list-group" id="categorylist">
        <div ng-click="select_category(0)" class="list-group-item " ng-class="active_category_id === 0 ? 'active' : ''">所有商品</div>
        <div ng-repeat="category in categories" ng-click="select_category(category.id)" class="list-group-item" ng-class="active_category_id === category.id ? 'active' : ''">
          {{category.name}}
        </div>
      </div>
    </div>
    <div class="col-md-9">
      <div class="row" id="goodslist">
        <div ng-repeat="goods in goods_to_list" class="col-md-3">
          <div class="thumbnail">
            <div ng-click='getGoodsDetail(goods.id)' class="caption text-center" data-gid="{{goods.id}}">
              <img ng-src="{{goods.pic}}" />
              <h5>{{goods.name}}</h5>
              <h4 class="pull-left text-danger">{{goods.price/100 | currency : "¥" : 2}}</h4>
              <div class="clearfix"></div>
            </div>
          </div>
          <div class="btn-group pull-right" role="group" style="position: absolute;right:30px;bottom:30px;">
            <button ng-click='goodsMinus(goods.id)' type="button" class="btn btn-sm btn-default">-</button>
            <div class="btn btn-sm btn-default goodsitem">{{goods.quantity}}</div>
            <button ng-click='goodsPlus(goods.id)' type="button" class="btn btn-sm btn-default">+</button>
          </div>
        </div>
      </div>
    </div>
  </div>
</div>
<jsp:include page="template/goodsDetails.jsp"></jsp:include>

<link href="<%=request.getContextPath()%>/css/index.css" rel="stylesheet" />