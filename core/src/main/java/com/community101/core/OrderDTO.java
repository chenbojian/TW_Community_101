package com.community101.core;

import java.util.List;

/**
 * Created by MiffyLiye on 17/07/2015.
 */
public class OrderDTO {
    private List<GoodsInSubmissionDTO> goodsList;
    private String phone;
    private String address;

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
}
