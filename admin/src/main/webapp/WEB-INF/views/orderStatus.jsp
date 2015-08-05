<%--
  Created by IntelliJ IDEA.
  User: chenjian
  Date: 7/30/15
  Time: 11:05 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="container">

  <ul class="list-group">
    <li class="list-group-item active">
      <h3></h3>
      <span class="badge" >总收入：{{orders_total_price/100 | currency : "¥" : 2}}</span>
      <span class="badge">数量：{{orders_total_num}}</span>
      <h4>订单汇总：</h4>
    </li>

  <div class="panel-group" id="accordion" role="tablist" aria-multiselectable="true">
    <div class="panel panel-default">
      <div class="panel-heading" role="tab" id="headingOne">
        <h4 class="panel-title">
          <li class="list-group-item">
          <span class="badge">{{new_total_price/100 | currency : "¥" : 2}}</span>
          <span class="badge">{{new_num}}</span>
          <a role="button" data-toggle="collapse" data-parent="#accordion" aria-expanded="false" aria-controls="collapseOne" data-target="#collapseOne">
            新订单
          </a>
          </li>
        </h4>
      </div>
      <div id="collapseOne" class="panel-collapse collapse in" role="tabpanel" aria-labelledby="headingOne">
        <div class="panel-body">

          <div ng-repeat="item in new_list">
            <div>
              订单号：<strong>{{item["orderId"]}}</strong><br/>
              下单时间：{{item["createTime"] | date:'yyyy-MM-dd HH:mm:ss'}}<br/>
              联系方式：{{item["telPhone"]}}<br/>
              配送地址：{{item["address"]}}
            </div>
            <table class="table">
              <thead>
              <tr>
                <th>商品名称</th>
                <th>商品单价</th>
                <th>选购数量</th>
              </tr>
              </thead>
              <tbody>
              <tr ng-repeat="goods in item.goodsInOrderDTOList">
                <td> {{goods.name}}</td>
                <td> {{goods.price/100 | currency : "¥" : 2}}</td>
                <td> {{goods.quantity}}</td>
              </tr>
              </tbody>
              <tfoot>
              <tr>
                <td colspan="3"><div align="right">总价：<strong>{{item["totalPrice"]/100 | currency : "¥" : 2}}</strong></div></td>
              </tr>
              </tfoot>
            </table>
          </div>

        </div>
      </div>
      </div>

    <div class="panel panel-default">
      <div class="panel-heading" role="tab" id="headingTwo">
        <h4 class="panel-title">
          <li class="list-group-item">
            <span class="badge">{{dispatching_total_price/100 | currency : "¥" : 2}}</span>
            <span class="badge">{{dispatching_num}}</span>
            <a role="button" data-toggle="collapse" data-parent="#accordion" aria-expanded="true" aria-controls="collapseTwo" data-target="#collapseTwo">
              配送订单
            </a>
          </li>
        </h4>
      </div>
      <div id="collapseTwo" class="panel-collapse collapse in" role="tabpanel" aria-labelledby="headingTwo">
        <div class="panel-body">

          <div ng-repeat="item in dispatching_list">
            <div>
              订单号：<strong>{{item["orderId"]}}</strong><br/>
              下单时间：{{item["createTime"] | date:'yyyy-MM-dd HH:mm:ss'}}<br/>
              联系方式：{{item["telPhone"]}}<br/>
              配送地址：{{item["address"]}}
            </div>
            <table class="table">
              <thead>
              <tr>
                <th>商品名称</th>
                <th>商品单价</th>
                <th>选购数量</th>
              </tr>
              </thead>
              <tbody>
              <tr ng-repeat="goods in item.goodsInOrderDTOList">
                <td> {{goods.name}}</td>
                <td> {{goods.price/100 | currency : "¥" : 2}}</td>
                <td> {{goods.quantity}}</td>
              </tr>
              </tbody>
              <tfoot>
              <tr>
                <td colspan="3"><div align="right">总价：<strong>{{item["totalPrice"]/100 | currency : "¥" : 2}}</strong></div></td>
              </tr>
              </tfoot>
            </table>
           </div>

        </div>
      </div>
    </div>

    <div class="panel panel-default">
      <div class="panel-heading" role="tab" id="headingThree">
        <h4 class="panel-title">
          <li class="list-group-item">
            <span class="badge">{{completed_total_price/100 | currency : "¥" : 2}}</span>
            <span class="badge">{{completed_num}}</span>
            <a role="button" data-toggle="collapse" data-parent="#accordion" aria-expanded="true" aria-controls="collapseThree" data-target="#collapseThree">
              完成订单
            </a>
          </li>
        </h4>
      </div>
      <div id="collapseThree" class="panel-collapse collapse in" role="tabpanel" aria-labelledby="headingThree">
        <div class="panel-body">

          <div ng-repeat="item in completed_list">
            <div>
              订单号：<strong>{{item["orderId"]}}</strong><br/>
              下单时间：{{item["createTime"] | date:'yyyy-MM-dd HH:mm:ss'}}<br/>
              联系方式：{{item["telPhone"]}}<br/>
              配送地址：{{item["address"]}}
            </div>
            <table class="table">
              <thead>
              <tr>
                <th>商品名称</th>
                <th>商品单价</th>
                <th>选购数量</th>
              </tr>
              </thead>
              <tbody>
              <tr ng-repeat="goods in item.goodsInOrderDTOList">
                <td> {{goods.name}}</td>
                <td> {{goods.price/100 | currency : "¥" : 2}}</td>
                <td> {{goods.quantity}}</td>
              </tr>
              </tbody>
              <tfoot>
              <tr>
                <td colspan="3"><div align="right">总价：<strong>{{item["totalPrice"]/100 | currency : "¥" : 2}}</strong></div></td>
              </tr>
              </tfoot>
            </table>
          </div>
        </div>

        </div>
      </div>
    </div>

   </ul>
  </div>
