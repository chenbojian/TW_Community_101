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

    @Autowired
    public OrderController(OrdersService ordersService, GoodsService goodsService, UserService userService){
        this.ordersService = ordersService;
        this.goodsService = goodsService;
        this.userService = userService;
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

}
