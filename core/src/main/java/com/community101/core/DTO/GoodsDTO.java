package com.community101.core.DTO;

/**
 * Created by MiffyLiye on 16/07/2015.
 */
public class GoodsDTO {
    private long id;
    private String name;
    private int price;
    private String pic; //pic url

    public GoodsDTO() {}

    public GoodsDTO(long id, String name, int price, String pic) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.pic = pic;
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
}
