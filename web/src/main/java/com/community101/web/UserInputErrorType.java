package com.community101.web;

/**
 * Created by MiffyLiye on 24/07/2015.
 */
public enum UserInputErrorType {
    ORDER_GOODS_LIST_EMPTY,
    ORDER_PHONE_INVALID,
    ORDER_ADDRESS_INVALID;

    public String getFriendlyMessage() {
        switch (this) {
            case ORDER_GOODS_LIST_EMPTY: return "订单商品列表为空。";
            case ORDER_PHONE_INVALID: return "订单联系电话无法识别。";
            case ORDER_ADDRESS_INVALID: return "订单派送地址无法识别。";
            default: return "未知错误。";
        }
    }
}
