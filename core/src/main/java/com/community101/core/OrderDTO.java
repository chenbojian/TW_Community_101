package com.community101.core;

import java.util.List;

/**
 * Created by MiffyLiye on 17/07/2015.
 */
public class OrderDTO {
    private List<GoodsInOrderDTO> goodsList;
    private String phoneNumber;
    private String address;

    public List<GoodsInOrderDTO> getGoodsList() {
        return goodsList;
    }

    public void setGoodsList(List<GoodsInOrderDTO> goodsList) {
        this.goodsList = goodsList;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
