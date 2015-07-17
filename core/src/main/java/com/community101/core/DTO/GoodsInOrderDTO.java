package com.community101.core.DTO;

/**
 * Created by MiffyLiye on 16/07/2015.
 */
public class GoodsInOrderDTO {
    private long id;
    private String name;
    private int price;
    private String pic; //pic url
    private int quantity;

    public GoodsInOrderDTO() {}

    public GoodsInOrderDTO(long id, String name, int price, String pic, int quantity) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.pic = pic;
        this.quantity = quantity;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
