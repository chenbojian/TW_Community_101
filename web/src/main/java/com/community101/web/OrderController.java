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
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
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

    private boolean is_fake = true;
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
        List<OrderInOrderManagerDTO> newOrders = transferOrder(newOrdersList);
        List<OrderInOrderManagerDTO> dispatchingOrders = transferOrder(dispatchingOrdersList);
        List<OrderInOrderManagerDTO> completedOrders = transferOrder(completedOrdersList);

        modelAndView.addObject("dispatchingOrdersList", dispatchingOrders);
        modelAndView.addObject("completedOrdersList", completedOrders);
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
    //angular
    @RequestMapping("/newOrders")
    @ResponseBody
    public String newOrders() throws Exception {
        List<Orders> ordersList = ordersService.listNewOrders();
        List<OrderInOrderManagerDTO> orders = new ArrayList<OrderInOrderManagerDTO>();
        for(Orders order:ordersList){
            OrderInOrderManagerDTO orderDTO = new OrderInOrderManagerDTO();
            orderDTO.setId(order.getId());
            orderDTO.setTotalPrice(order.getTotalPrice());
            orders.add(orderDTO);
        }
            String json = gson.toJson(orders);
        return json;
    }

    @RequestMapping("/dispatchingOrders")
    @ResponseBody
    public String dispatchingOrders() throws Exception {
        List<Orders> ordersList = ordersService.listDispatchingOrders();
        List<OrderInOrderManagerDTO> orders = new ArrayList<OrderInOrderManagerDTO>();
        for(Orders order:ordersList){
            OrderInOrderManagerDTO orderDTO = new OrderInOrderManagerDTO();
            orderDTO.setId(order.getId());
            orderDTO.setTotalPrice(order.getTotalPrice());
            orders.add(orderDTO);
        }
        String json = gson.toJson(orders);
        return json;
    }

    @RequestMapping("/completedOrders")
    @ResponseBody
    public String completedOrders() throws Exception {
        List<Orders> ordersList = ordersService.listCompletedOrders();
        List<OrderInOrderManagerDTO> orders = new ArrayList<OrderInOrderManagerDTO>();
        for(Orders order:ordersList){
            OrderInOrderManagerDTO orderDTO = new OrderInOrderManagerDTO();
            orderDTO.setId(order.getId());
            orderDTO.setTotalPrice(order.getTotalPrice());
            orders.add(orderDTO);
        }
        String json = gson.toJson(orders);
        return json;
    }

    //

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
    @ResponseStatus(HttpStatus.CREATED)
    public long submitOrder(long[] id, int[] quantity, String phone, String address) {
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setAddress(address);
        orderDTO.setPhone(phone);
        orderDTO.setGoodsList(new LinkedList<GoodsInSubmissionDTO>());
        for (int i = 0; i < id.length; i++) {
            GoodsInSubmissionDTO goodsInSubmissionDTO = new GoodsInSubmissionDTO(id[i], quantity[i]);
            orderDTO.getGoodsList().add(goodsInSubmissionDTO);
        }

        Orders order = new Orders();

        order.setAddress(orderDTO.getAddress());
        User user = userService.findUserByTel(orderDTO.getPhone());
        if (user == null) {
            user = new User();
            user.setTelPhone(orderDTO.getPhone());
            userService.addUser(user);
        }
        order.setUser(user);

        List<GoodsInSubmissionDTO> goodsInSubmissionDTOList = orderDTO.getGoodsList();
        if (goodsInSubmissionDTOList == null) {
            goodsInSubmissionDTOList = new LinkedList<GoodsInSubmissionDTO>();
        }
        Set<OrderGoods> orderGoodsSet = new LinkedHashSet<OrderGoods>();
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
        order.setOrderGoodses(orderGoodsSet);
        order.setTotalPrice(order.getBillTotal());
        order.setStatus("new");
        order.setCreateTime(new Timestamp(System.currentTimeMillis()));
        ordersService.addOrder(order);
        return order.getId();
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
