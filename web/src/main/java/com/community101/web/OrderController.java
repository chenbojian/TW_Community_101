package com.community101.web;

import com.community101.web.DTO.OrderDetailDTO;
import com.community101.web.DTO.OrderInOrderManagerDTO;
import com.community101.core.Orders;
import com.community101.core.service.GoodsService;
import com.community101.core.service.OrdersService;
import com.google.gson.Gson;
import com.community101.core.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.*;

/**
 * Created by chenjian on 7/17/15.
 */
@RestController
@RequestMapping("/api/order")
public class OrderController {
    static Gson gson = new Gson();

    private OrdersService ordersService;

    @Autowired
    public OrderController(OrdersService ordersService, GoodsService goodsService, UserService userService){
        this.ordersService = ordersService;
    }

    @RequestMapping("/newOrders")
    public List<OrderDetailDTO> getNewOrdersList(){
        List<Orders> newOrdersList=ordersService.listNewOrders();
        return Mapper.makeOrderDetailDTOList(newOrdersList);
    }

    @RequestMapping("/dispatchingOrders")
    public List<OrderDetailDTO> getDispatchingOrdersList(){
        List<Orders> dispatchingOrdersList=ordersService.listDispatchingOrders();
        return Mapper.makeOrderDetailDTOList(dispatchingOrdersList);
    }

    @RequestMapping("/completedOrders")
    public List<OrderDetailDTO> getCompletedOrdersList(){
        List<Orders> completedOrdersList=ordersService.listCompletedOrders();
        return Mapper.makeOrderDetailDTOList(completedOrdersList);
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


    @RequestMapping(value = "/detail")
    public OrderDetailDTO getOrderDetail(long orderId){
        Orders orders=ordersService.findOrdersById(orderId);
        return Mapper.makeOrderDetailDTO(orders);
    }

}
