package com.community101.core;

/**
 * Created by MiffyLiye on 16/07/2015.
 */
public class GoodsInSubmissionDTO {
    private long id;
    private int quantity;

    public GoodsInSubmissionDTO() {}

    public GoodsInSubmissionDTO(long id, int quantity) {
        this.id = id;
        this.quantity = quantity;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
