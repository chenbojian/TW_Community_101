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
    private Integer count;

    @Column(name="goods_price")
    private Integer goodsPrice;

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

    public void setGoods(Goods goods){
        this.goodsName=goods.getName();
        this.goodsCategoryName=goods.getCategory().getName();
        this.goodsDescription=goods.getDescription();
        this.goodsPictureUrl=goods.getPictureUrl();
        this.goodsPrice=goods.getPrice();
    }

    public Goods getGoods() {
        Goods goods = new Goods();
        goods.setName(goodsName);
        goods.setCategory(new Category());
        goods.getCategory().setName(goodsCategoryName);
        goods.setDescription(goodsDescription);
        goods.setPictureUrl(goodsPictureUrl);
        goods.setPrice(goodsPrice);
        return goods;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public Integer getGoodsPrice() {
        return goodsPrice;
    }

    public void setGoodsPrice(Integer goodsPrice) {
        this.goodsPrice = goodsPrice;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }
}
