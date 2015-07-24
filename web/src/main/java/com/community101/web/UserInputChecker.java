package com.community101.web;

import com.community101.core.Orders;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by MiffyLiye on 24/07/2015.
 */
public class UserInputChecker {
    public static boolean isOrderGoodsListEmpty(Orders order) {
        return order.isEmpty();
    }

    public static boolean isOrderPhoneValid(Orders order) {
        return order.isValidPhone();
    }

    public static boolean isOrderAddressValid(Orders order) {
        return order.isValidAddress();
    }

    public static List<UserInputErrorType> findOrderErrors(Orders order) {
        List<UserInputErrorType> errorTypeList = new LinkedList<UserInputErrorType>();
        if (isOrderGoodsListEmpty(order)) {
            errorTypeList.add(UserInputErrorType.ORDER_GOODS_LIST_EMPTY);
        }
        if (!isOrderPhoneValid(order)) {
            errorTypeList.add(UserInputErrorType.ORDER_PHONE_INVALID);
        }
        if (!isOrderAddressValid(order)) {
            errorTypeList.add(UserInputErrorType.ORDER_ADDRESS_INVALID);
        }

        return errorTypeList;
    }

    public static List<String> getErrorMessages(List<UserInputErrorType> errorTypeList) {
        List<String> errorMessages = new LinkedList<String>();
        for (UserInputErrorType errorType : errorTypeList) {
            errorMessages.add(errorType.getFriendlyMessage());
        }
        return errorMessages;
    }

    public static List<String> findOrderErrorMessages(Orders order) {
        return getErrorMessages(findOrderErrors(order));
    }
}
