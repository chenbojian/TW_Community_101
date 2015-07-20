package com.community101.core.DTO;

/**
 * Created by chenjian on 7/20/15.
 */
public class OrderInOrderManagerDTO {

    private long id;
    private int totalPrice;

    public int getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(int totalPrice) {
        this.totalPrice = totalPrice;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
