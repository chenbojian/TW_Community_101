package com.community101.core;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Set;

/**
 * Created by jiaoming on 7/16/15.
 */
@Entity
@Table(name = "ORDERS")
public class Orders {
    @Id
    @Column(name = "id")
    @GeneratedValue
    private long id;

    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;

    @Column(name="address")
    private String address;

    @Column(name="create_time")
    private Timestamp createTime;

    @Column(name="status")
    private String status;

    @Column(name="total_price")
    private Integer totalPrice;

    @OneToMany(mappedBy = "orders")
    private Set<OrderGoods> orderGoodses;

    public Orders(){

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Set<OrderGoods> getOrderGoodses() {
        return orderGoodses;
    }

    public void setOrderGoodses(Set<OrderGoods> orderGoodses) {
        this.orderGoodses = orderGoodses;
    }

    public Integer getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Integer totalPrice) {
        this.totalPrice = totalPrice;
    }

    public int getBillTotal() {
        int total = 0;
        for (OrderGoods orderGoods : orderGoodses) {
            total = total + orderGoods.getGoodsPrice() * orderGoods.getCount();
        }
        return total;
    }
}
