package com.community101.web;

import com.community101.core.DTO.OrderDTO;
import com.community101.core.DTO.OrderInOrderManagerDTO;
import com.community101.core.Orders;
import com.community101.core.User;
import com.community101.core.service.OrdersService;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by chenjian on 7/17/15.
 */
@RestController
@RequestMapping("/api/order")
public class OrderController {
    private boolean is_fake = true;
    private OrdersService ordersService;
    static Gson gson = new Gson();

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
    @Autowired
    public OrderController(OrdersService ordersService){
        this.ordersService = ordersService;
    }

    @RequestMapping("/all")
    public List<Orders> listAllOrders() {
        List<Orders> ordersList = new ArrayList<Orders>();
        ordersList.add(order1);
        ordersList.add(order2);
        return ordersList;
    }

    @RequestMapping("/orderManager")
    public ModelAndView orderManagerPage() {
        ModelAndView modelAndView = new ModelAndView("order-manager");
        return modelAndView;
    }

    @RequestMapping("/newOrder")
    @ResponseBody
    public void newOrder(int count, HttpServletResponse response) throws Exception {
        PrintWriter writer = response.getWriter();
        List<Orders> ordersList = ordersService.listNewOrders();
        List<OrderInOrderManagerDTO> orders = new ArrayList<OrderInOrderManagerDTO>();
        for(Orders order:ordersList){
            OrderInOrderManagerDTO orderDTO = new OrderInOrderManagerDTO();
            orderDTO.setId(order.getId());
            orderDTO.setTotalPrice(order.getTotalPrice());
            orders.add(orderDTO);
        }
        if(count<ordersList.size()){
            OrderInOrderManagerDTO order = orders.get(count);
            String json = gson.toJson(order);
            writer.print(json);
        }
    }

    @RequestMapping("/dispatchingOrder")
    public void dispatchingOrder(int count, HttpServletResponse response) throws Exception{
        PrintWriter writer = response.getWriter();
        List<Orders> ordersList = ordersService.listDispatchingOrders();
        List<OrderInOrderManagerDTO> orders = new ArrayList<OrderInOrderManagerDTO>();
        for(Orders order:ordersList){
            OrderInOrderManagerDTO orderDTO = new OrderInOrderManagerDTO();
            orderDTO.setId(order.getId());
            orderDTO.setTotalPrice(order.getTotalPrice());
            orders.add(orderDTO);
        }
        if(count<ordersList.size()){
            OrderInOrderManagerDTO order = orders.get(count);
            String json = gson.toJson(order);
            writer.print(json);
        }
    }

    @RequestMapping("dispatchOrder")
    public void dispatchOrder(long orderId){
        ordersService.dispatchOrder(orderId);
    }

    @RequestMapping(value = "/submit", method = RequestMethod.POST)
    public long submitOrder(OrderDTO order) {
        if (is_fake) {
            long orderId = 12345;

            return orderId;
        }
        else {
            throw new NotImplementedException();
        }
    }



    class OrderBuilder{

        private long id;
        private User user;
        private String address;
        private Timestamp createTime;
        private String status;
        private int price;

        public OrderBuilder() {
        }

        public OrderBuilder givenBuilder() {
            return this;
        }

        public OrderBuilder withId(long id) {
            this.id = id;
            return this;
        }

        public OrderBuilder withUser(User user) {
            this.user = user;
            return this;
        }

        public OrderBuilder withAddress(String address) {
            this.address = address;
            return this;
        }

        public OrderBuilder withTimestamp(Timestamp createTime) {
            this.createTime = createTime;
            return this;
        }

        public OrderBuilder withStatus(String status) {
            this.status = status;
            return this;
        }

        public OrderBuilder withPrice(int price) {
            this.price = price;
            return this;
        }

        public Orders build() {
            Orders order = new Orders();
            order.setId(this.id);
            order.setUser(this.user);
            order.setAddress(this.address);
            order.setCreateTime(this.createTime);
            order.setStatus(this.status);
            order.setTotalPrice(1200);
            return order;
        }

    }
}
