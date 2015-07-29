package com.community101.admin.DTO;

import java.sql.Timestamp;
import java.util.List;

/**
 * Created by jiaoming on 7/20/15.
 */
public class OrderDetailDTO {
    private Long orderId;
    private String status;
    private String telPhone;
    private String address;
    private Timestamp createTime;
    private Integer totalPrice;
    private List<GoodsInOrderDTO>  goodsInOrderDTOList;

    public OrderDetailDTO(){}

    public OrderDetailDTO(Long orderId,String status,String telPhone,String address, Timestamp createTime,Integer totalPrice,List<GoodsInOrderDTO>  goodsInOrderDTOList){
        this.orderId=orderId;
        this.status=status;
        this.telPhone=telPhone;
        this.address=address;
        this.createTime=createTime;
        this.totalPrice=totalPrice;
        this.goodsInOrderDTOList=goodsInOrderDTOList;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTelPhone() {
        return telPhone;
    }

    public void setTelPhone(String telPhone) {
        this.telPhone = telPhone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public Integer getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Integer totalPrice) {
        this.totalPrice = totalPrice;
    }

    public List<GoodsInOrderDTO> getGoodsInOrderDTOList() {
        return goodsInOrderDTOList;
    }

    public void setGoodsInOrderDTOList(List<GoodsInOrderDTO> goodsInOrderDTOList) {
        this.goodsInOrderDTOList = goodsInOrderDTOList;
    }
}
