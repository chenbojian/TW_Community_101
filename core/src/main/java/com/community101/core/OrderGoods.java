package com.community101.core;

import javax.persistence.*;

/**
 * Created by jiaoming on 7/16/15.
 */
@Entity
@Table(name = "ORDER_GOODS")
public class OrderGoods {
    @Id
    @Column(name = "id")
    @GeneratedValue
    private long id;

    @ManyToOne
    @JoinColumn(name="order_id")
    private Orders orders;

    @Column(name="count")
    private int count;

    @Column(name="goods_name")
    private String goodsName;

    @Column(name="goods_description")
    private String goodsDescription;

    @Column(name="goods_category_name")
    private String goodsCategoryName;

    @Column(name="goods_picture_url")
    private String goodsPictureUrl;

    public OrderGoods(){

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public String getGoodsDescription() {
        return goodsDescription;
    }

    public void setGoodsDescription(String goodsDescription) {
        this.goodsDescription = goodsDescription;
    }

    public String getGoodsCategoryName() {
        return goodsCategoryName;
    }

    public void setGoodsCategoryName(String goodsCategoryName) {
        this.goodsCategoryName = goodsCategoryName;
    }

    public String getGoodsPictureUrl() {
        return goodsPictureUrl;
    }

    public void setGoodsPictureUrl(String goodsPictureUrl) {
        this.goodsPictureUrl = goodsPictureUrl;
    }

    public Orders getOrders() {
        return orders;
    }

    public void setOrders(Orders orders) {
        this.orders = orders;
    }
}
