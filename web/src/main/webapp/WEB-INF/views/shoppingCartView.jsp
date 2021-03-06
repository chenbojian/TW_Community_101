<%@ page contentType="text/html;charset=UTF-8" language="java" %>

  <link href="<%=request.getContextPath()%>/css/index.css" rel="stylesheet"/>
  <style>
    table th, td {
      text-align: center;
      vertical-align: middle !important;
    }

    table img {
      height: 60px;
      width: 60px;
    }

    table span{
      cursor: pointer;
    }
  </style>

<div class="container">
  <div class="row">
    <div class="col-md-12">
      <div class="jumbotron">
        <h1 class="text-center">
          <kbd>社区101</kbd>
        </h1>
      </div>
    </div>
  </div>
  <hr>
  <div>
    <table class="table">
      <tr>
        <th colspan="2">商品信息</th>
        <th>单价</th>
        <th>数量</th>
        <th>价格</th>
        <th>操作</th>
      </tr>
      <tr ng-repeat="item in selected_items" class="warning">
        <td style="width:120px;"><img ng-src="{{item.pic}}"/></td>
        <td><a href="javascript:void(0);" ng-click="getGoodsDetail(item.id)">{{item.name}}</a></td>
        <td style="width:120px;">{{item.price/100 | currency : "¥" : 2}}</td>
        <td style="width:120px;">
          <div class="input-group">
                        <span class="input-group-addon" ng-show="can_submit_order"
                              ng-click="less_selected_item($index)">-
                        </span>
            <input type="text" class="form-control" ng-model="selected_items[$index].quantity" value="{{item.quality}}" />
                        <span class="input-group-addon" ng-show="can_submit_order"
                              ng-click="more_selected_item($index)">+
                        </span>
          </div>
        </td>
        <td style="width:120px;">{{item.price/100 * item.quantity | currency : "¥" : 2}}</td>
        <td style="width:120px;">
          <button type="button" class="btn btn-default" ng-show="can_submit_order"
                  ng-click="remove_selected_item($index)">移除
          </button>
        </td>
      </tr>
    </table>
  </div>
  <div class="text-right">
    总价:<strong>{{bill.total/100 | currency : "¥" : 2}}</strong>
  </div>
  <form class="form-horizontal">
    <div class="form-group">
      <label class="col-md-1 control-label">联系电话:</label>
      <div class="col-md-11">
        <input class="form-control" ng-model="phone" pattern="^[0-9]{11}$" placeholder="请输入电话">
      </div>
    </div>

    <div class="form-group">
      <label class="col-md-1 control-label">送货地址:</label>
      <div class="col-md-11">
        <input class="form-control col-md-10" ng-model="address" placeholder="请输入地址">
      </div>
    </div>

    <div class="text-right" ng-model="can_submit_order" ng-show="can_submit_order">
      <h2>{{message}}</h2>
      <button type="button" class="btn btn-success" ng-click="submit()">提交</button>
    </div>
  </form>

  <div ng-model="order_submitted" ng-show="order_submitted">
    <p>订单号: {{order_id}}</p>

    <h2><a href="#/order-details">查看订单状态</a></h2>
  </div>

  <jsp:include page="template/goodsDetails.jsp"></jsp:include>

</div>
