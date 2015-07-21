package com.community101.web;

import com.community101.core.DTO.GoodsInSubmissionDTO;
import com.community101.core.DTO.OrderDTO;
import com.community101.core.DTO.OrderInOrderManagerDTO;
import com.community101.core.Goods;
import com.community101.core.OrderGoods;
import com.community101.core.Orders;
import com.community101.core.User;
import com.community101.core.service.GoodsService;
import com.community101.core.service.OrdersService;
import com.google.gson.Gson;
import com.community101.core.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.util.*;

/**
 * Created by chenjian on 7/17/15.
 */
@RestController
@RequestMapping("/api/order")
public class OrderController {
    static Gson gson = new Gson();

    private boolean is_fake = false;
    private OrdersService ordersService;
    private GoodsService goodsService;
    private UserService userService;

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
    public OrderController(OrdersService ordersService, GoodsService goodsService, UserService userService){
        this.ordersService = ordersService;
        this.goodsService = goodsService;
        this.userService = userService;
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
        List<Orders> newOrdersList = ordersService.listNewOrders();
        List<Orders> dispatchingOrdersList = ordersService.listDispatchingOrders();
        List<Orders> completedOrdersList = ordersService.listCompletedOrders();
        List<Orders> cancelOrdersList = ordersService.listCancelOrders();
        List<OrderInOrderManagerDTO> newOrders = transferOrder(newOrdersList);
        List<OrderInOrderManagerDTO> dispatchingOrders = transferOrder(dispatchingOrdersList);
        List<OrderInOrderManagerDTO> completedOrders = transferOrder(completedOrdersList);
        List<OrderInOrderManagerDTO> cancelOrders = transferOrder(cancelOrdersList);

        modelAndView.addObject("dispatchingOrdersList", dispatchingOrders);
        modelAndView.addObject("completedOrdersList", completedOrders);
        modelAndView.addObject("cancelOrdersList", cancelOrders);
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

    @RequestMapping("/getOrder")
    public void dispatchingOrder(long orderId, HttpServletResponse response) throws Exception{
        PrintWriter writer = response.getWriter();
        Orders orders = ordersService.findOrdersById(orderId);

        OrderInOrderManagerDTO orderDTO = new OrderInOrderManagerDTO();
        orderDTO.setId(orders.getId());
        orderDTO.setTotalPrice(orders.getTotalPrice());

        String json = gson.toJson(orderDTO);
        System.out.print(json);
        writer.print(json);
    }

    @RequestMapping("/dispatchOrder")
    public void dispatchOrder(long orderId){
        ordersService.dispatchOrder(orderId);
    }

    @RequestMapping("/completeOrder")
    public void completeOrder(long orderId){
        ordersService.completeOrder(orderId);
    }

    @RequestMapping("/cancelOrder")
    public void cancelOrder(long orderId){
        ordersService.cancelOrder(orderId);
    }

    @RequestMapping(value = "/submit", method = RequestMethod.POST)
    public long submitOrder(OrderDTO orderDTO) {
        if (is_fake) {
            long orderId = 12345;

            return orderId;
        }
        else {
            Orders order = new Orders();
            order.setAddress(orderDTO.getAddress());
            User user = userService.findUserByTel(orderDTO.getPhone());
            if (user == null) {
                user = new User();
                user.setTelPhone(orderDTO.getPhone());
                userService.addUser(user);
            }
            order.setUser(user);
            if (user.getOrderses() == null) {
                user.setOrderses(new LinkedHashSet<Orders>());
            }
            user.getOrderses().add(order);

            Set<OrderGoods> orderGoodsSet = new LinkedHashSet<OrderGoods>();
            order.setOrderGoodses(orderGoodsSet);
            List<GoodsInSubmissionDTO> goodsInSubmissionDTOList = orderDTO.getGoodsList();
            if (goodsInSubmissionDTOList == null) {
                goodsInSubmissionDTOList = new LinkedList<GoodsInSubmissionDTO>();
            }
            for (GoodsInSubmissionDTO goodsInSubmissionDTO : goodsInSubmissionDTOList) {
                Goods goods = goodsService.findGoodsById(goodsInSubmissionDTO.getId());
                OrderGoods orderGoods = new OrderGoods();
                orderGoods.setId(goods.getId());
                orderGoods.setCount(goodsInSubmissionDTO.getQuantity());
                orderGoods.setGoodsCategoryName(goods.getCategory().getName());
                orderGoods.setGoodsDescription(goods.getDescription());
                orderGoods.setGoodsName(goods.getName());
                orderGoods.setGoodsPrice(goods.getPrice());
                orderGoods.setGoodsPictureUrl(goods.getPictureUrl());
                orderGoods.setOrders(order);
            }
            order.setTotalPrice(order.getBillTotal());
            order.setStatus("new");
            order.setCreateTime(new Timestamp(System.currentTimeMillis()));
            ordersService.addOrder(order);
            return order.getId();
        }
    }

    private List<OrderInOrderManagerDTO> transferOrder(List<Orders> ordersList){
        List<OrderInOrderManagerDTO> orders = new ArrayList<OrderInOrderManagerDTO>();
        for(Orders order:ordersList){
            OrderInOrderManagerDTO orderDTO = new OrderInOrderManagerDTO();
            orderDTO.setId(order.getId());
            orderDTO.setTotalPrice(order.getTotalPrice());
            orders.add(orderDTO);
        }
        return orders;
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
