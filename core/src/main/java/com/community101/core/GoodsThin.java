package com.community101.core;

/**
 * Created by MiffyLiye on 16/07/2015.
 */
public class GoodsThin {
    private long id;
    private String name;
    private String price;
    private String pic; //pic url

    public GoodsThin() {}

    public GoodsThin(long id, String name, String price, String pic) {
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

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }
}
