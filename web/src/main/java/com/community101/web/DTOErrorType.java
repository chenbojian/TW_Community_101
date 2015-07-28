package com.community101.web;

/**
 * Created by MiffyLiye on 24/07/2015.
 */
public enum DTOErrorType {
    ORDER_GOODS_LIST_EMPTY("订单商品列表为空。"),
    ORDER_PHONE_INVALID("订单联系电话无法识别。"),
    ORDER_ADDRESS_INVALID("订单派送地址无法识别。");

    private String message;

    DTOErrorType(String message) {
        this.message = message;
    }

    public String getFriendlyMessage() {
        return message;
    }
}
