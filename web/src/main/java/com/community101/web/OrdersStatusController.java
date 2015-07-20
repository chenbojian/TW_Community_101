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
        List<Orders> cancelOrdersList=ordersService.listCancelOrders();
        modelAndView.addObject("newOrdersList",newOrdersList);
        modelAndView.addObject("dispatchingOrdersList",dispatchingOrdersList);
        modelAndView.addObject("completedOrdersList",completedOrdersList);
        modelAndView.addObject("cancelOrdersList",cancelOrdersList);
        return modelAndView;
    }

    @RequestMapping(value = "/customerOrders")
    public ModelAndView showCustomerOrders(){
        ModelAndView modelAndView=new ModelAndView("customer-orders-status");
        long id=2;

        Orders orders=ordersService.findOrdersById(id);
        modelAndView.addObject("orders",orders);
        return modelAndView;
    }

    @RequestMapping(value = "/detail")
    public OrderDetailDTO getOrderDetail(long orderId){
        Orders orders=ordersService.findOrdersById(orderId);
        OrderDetailDTO orderDetailDTO=new OrderDetailDTO();
        orderDetailDTO.setOrderId(orders.getId());
        orderDetailDTO.setStatus(orders.getStatus());
        orderDetailDTO.setTelPhone(orders.getUser().getTelPhone());
        orderDetailDTO.setAddress(orders.getAddress());
        orderDetailDTO.setCreateTime(orders.getCreateTime());
        Set<OrderGoods> orderGoodses=orders.getOrderGoodses();
        List<GoodsInOrderDTO> goodsInOrderDTOs=new ArrayList<GoodsInOrderDTO>();
        for(OrderGoods orderGoods:orderGoodses){
            GoodsInOrderDTO goodsInOrderDTO=new GoodsInOrderDTO(orderGoods.getId(),orderGoods.getGoodsName(),
                    orderGoods.getGoodsPrice(),orderGoods.getGoodsPictureUrl(),orderGoods.getCount());
            goodsInOrderDTOs.add(goodsInOrderDTO);
        }
        return orderDetailDTO;
    }
}
