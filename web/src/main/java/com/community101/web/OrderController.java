package com.community101.web;

import com.community101.core.Orders;
import com.community101.core.User;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by chenjian on 7/17/15.
 */
@RestController
@RequestMapping("/api")
public class OrderController {

    private OrderBuilder orderBuilder = new OrderBuilder();
    Orders order1 = orderBuilder.givenBuilder()
            .withAddress("community101 unit 5 602")
            .withId(1)
            .withUser(new User())
            .withStatus("new")
            .withPrice(1200)
            .build();
    Orders order2 = orderBuilder.givenBuilder()
            .withAddress("community101 unit 5 302")
            .withId(1)
            .withUser(new User())
            .withStatus("dispatching")
            .withPrice(1000)
            .build();

    @RequestMapping("/order/all")
    public List<Orders> listAllOrders(){
        List<Orders> ordersList = new ArrayList<Orders>();
        ordersList.add(order1);
        ordersList.add(order2);
        return ordersList;
    }

    private class OrderBuilder{
        private long id;
        private User user;
        private String address;
        private Timestamp createTime;
        private String status;
        private int price;

        public OrderBuilder(){
        }

        public OrderBuilder givenBuilder(){
            return this;
        }

        public OrderBuilder withId(long id){
            this.id = id;
            return this;
        }

        public OrderBuilder withUser(User user){
            this.user = user;
            return this;
        }

        public OrderBuilder withAddress(String address){
            this.address = address;
            return this;
        }

        public OrderBuilder withTimestamp(Timestamp createTime){
            this.createTime = createTime;
            return this;
        }

        public OrderBuilder withStatus(String status){
            this.status = status;
            return this;
        }

        public OrderBuilder withPrice(int price){
            this.price = price;
            return this;
        }

        public Orders build(){
            Orders order = new Orders();
            order.setId(this.id);
            order.setUser(this.user);
            order.setAddress(this.address);
            order.setCreateTime(this.createTime);
            order.setStatus(this.status);
            order.setTotalPrice(12);
            return order;
        }

    }
}
