package com.community101.admin.api;

import com.community101.admin.DTO.OrderDetailDTO;
import com.community101.admin.DTO.OrderInOrderManagerDTO;
import com.community101.core.Orders;
import com.community101.core.service.GoodsService;
import com.community101.core.service.OrdersService;
import com.community101.admin.Mapper;
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
    public OrderController(OrdersService ordersService){
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

    @RequestMapping("/hasNewOrder")
    public NewOrderMSG hasNewOrder(HttpServletResponse response) throws Exception{
        if(ordersService.hasNewOrders()){
            ordersService.cleanNewOrdersTable();
            return new NewOrderMSG("yes");
        }else return null;
    }

    @RequestMapping("/dispatchOrder")
    public boolean dispatchOrder(long orderId){
        return ordersService.dispatchOrder(orderId);
    }

    @RequestMapping("/completeOrder")
    public boolean completeOrder(long orderId){
        return ordersService.completeOrder(orderId);
    }

    @RequestMapping("/cancelOrder")
    public boolean cancelOrder(long orderId){
        return ordersService.cancelOrder(orderId);
    }

    @RequestMapping(value = "/detail")
    public OrderDetailDTO getOrderDetail(long orderId){
        Orders orders=ordersService.findOrdersById(orderId);
        return Mapper.makeOrderDetailDTO(orders);
    }

    class NewOrderMSG{
        public NewOrderMSG(String s){
            this.hasNewOrders = s;
        }

        public String getHasNewOrders() {
            return hasNewOrders;
        }

        public void setHasNewOrders(String hasNewOrders) {
            this.hasNewOrders = hasNewOrders;
        }

        private String hasNewOrders;
    }
}
