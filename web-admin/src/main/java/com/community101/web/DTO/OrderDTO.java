package com.community101.web.DTO;

import com.community101.core.service.GoodsService;
import com.community101.web.validator.Checker;
import com.community101.web.validator.DTOErrorType;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by MiffyLiye on 17/07/2015.
 */
public class OrderDTO {
    private List<GoodsInSubmissionDTO> goodsList;
    private String phone;
    private String address;

    private GoodsService goodsService;

    private List<Checker> checkers = null;

    private OrderDTO() {
        checkers = new LinkedList<Checker>();
        checkers.add(new OrderEmptyChecker());
        checkers.add(new OrderInvalidPhoneChecker());
        checkers.add(new OrderInvalidAddressChecker());
    }

    @Autowired
    public OrderDTO(GoodsService goodsService) {
        this();
        this.goodsService = goodsService;
    }

    public List<GoodsInSubmissionDTO> getGoodsList() {
        return goodsList;
    }

    public void setGoodsList(List<GoodsInSubmissionDTO> goodsList) {
        this.goodsList = goodsList;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public List<Checker> getCheckers() {
        return checkers;
    }

    public void setCheckers(List<Checker> checkers) {
        this.checkers = checkers;
    }

    public class OrderEmptyChecker extends Checker {
        public boolean checkError(Object obj) {
            goodsList = ((OrderDTO) obj).getGoodsList();
            boolean isEmpty = true;
            if (goodsList == null) {
                return isEmpty;
            }
            for (GoodsInSubmissionDTO goods : goodsList) {
                if (goodsService.findGoodsById(goods.getId()) != null && goods.getQuantity() > 0) {
                    isEmpty = false;
                }
            }
            return isEmpty;
        }

        public DTOErrorType getErrorType() {
            return DTOErrorType.ORDER_GOODS_LIST_EMPTY;
        }

    }

    public class OrderInvalidPhoneChecker extends Checker {
        public boolean checkError(Object obj) {
            String phone = ((OrderDTO) obj).getPhone();
            if (phone == null) {
                return true;
            }
            return !phone.matches("^\\d{11}$");
        }

        public DTOErrorType getErrorType() {
            return DTOErrorType.ORDER_PHONE_INVALID;
        }
    }

    public class OrderInvalidAddressChecker extends Checker {
        public boolean checkError(Object obj) {
            String address = ((OrderDTO) obj).getAddress();
            return address == null || address == "";
        }

        public DTOErrorType getErrorType() {
            return DTOErrorType.ORDER_ADDRESS_INVALID;
        }
    }

    public List<DTOErrorType> getErrors() {
        List<DTOErrorType> errorTypes = new LinkedList<DTOErrorType>();
        for (Checker checker : getCheckers()) {
            if (checker.checkError(this)) {
                errorTypes.add(checker.getErrorType());
            }
        }
        return errorTypes;
    }

    public List<String> getErrorMessages() {
        List<String> errorMessages = new LinkedList<String>();
        List<DTOErrorType> errorTypeList = getErrors();
        for (DTOErrorType errorType : errorTypeList) {
            errorMessages.add(errorType.getFriendlyMessage());
        }
        return errorMessages;
    }
}
