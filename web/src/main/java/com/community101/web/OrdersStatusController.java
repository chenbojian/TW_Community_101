package com.community101.web;

import com.community101.core.DTO.GoodsInOrderDTO;
import com.community101.core.DTO.OrderDetailDTO;
import com.community101.core.OrderGoods;
import com.community101.core.Orders;
import com.community101.core.service.OrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Created by jiaoming on 7/17/15.
 */
@RestController
@RequestMapping("/ordersStatus")
public class OrdersStatusController {
    private OrdersService ordersService;

    @Autowired
    public OrdersStatusController(OrdersService ordersService){
        this.ordersService=ordersService;
    }

    @RequestMapping("/")
    public ModelAndView listOrdersStatus(){
        ModelAndView modelAndView=new ModelAndView("orders-status-list");
        List<Orders> newOrdersList=ordersService.listNewOrders();
        List<Orders> dispatchingOrdersList=ordersService.listDispatchingOrders();
        List<Orders> completedOrdersList=ordersService.listCompletedOrders();
        modelAndView.addObject("newOrdersList",newOrdersList);
        modelAndView.addObject("dispatchingOrdersList",dispatchingOrdersList);
        modelAndView.addObject("completedOrdersList",completedOrdersList);
        return modelAndView;
    }

    @RequestMapping(value = "/newOrdersList")
    public List<OrderDetailDTO> getNewOrdersList(){
        List<Orders> newOrdersList=ordersService.listNewOrders();
        return Mapper.makeOrderDetailDTOList(newOrdersList);
    }

    @RequestMapping(value = "/dispatchingOrdersList")
    public List<OrderDetailDTO> getDispatchingOrdersList(){
        List<Orders> dispatchingOrdersList=ordersService.listDispatchingOrders();
        return Mapper.makeOrderDetailDTOList(dispatchingOrdersList);
    }

    @RequestMapping(value = "/completedOrdersList")
    public List<OrderDetailDTO> getCompletedOrdersList(){
        List<Orders> completedOrdersList=ordersService.listCompletedOrders();
        return Mapper.makeOrderDetailDTOList(completedOrdersList);
    }

    @RequestMapping(value = "/detail")
    public OrderDetailDTO getOrderDetail(long orderId){
        Orders orders=ordersService.findOrdersById(orderId);
        return Mapper.makeOrderDetailDTO(orders);
    }
}
