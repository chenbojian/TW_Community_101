<%--
  Created by IntelliJ IDEA.
  User: MiffyLiye
  Date: 30/07/2015
  Time: 15:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="modal fade" id="goodsDetail" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
        <h4 class="modal-title" id="myModalLabel">[goodsname]</h4>
      </div>
      <div class="modal-body text-center">
        <div><img id="goodsDetailPicture"/></div>
        <div class="text-left">
          <h4>详细描述：</h4>
          <div><hr /></div>
          <p id="goodsDetailDescription">[goodsdescription]</p>
        </div>
        <div>价格：¥<span id="goodsDetailPrice">[goodsprice] </span></div>
      </div>
    </div>
  </div>
</div>

