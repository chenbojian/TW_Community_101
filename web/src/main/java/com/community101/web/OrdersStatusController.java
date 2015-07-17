package com.community101.web;

import com.community101.core.Orders;
import com.community101.core.service.OrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * Created by jiaoming on 7/17/15.
 */
@RestController
@RequestMapping("ordersStatus")
public class OrdersStatusController {
    private OrdersService ordersService;

    @Autowired
    public OrdersStatusController(OrdersService ordersService){
        this.ordersService=ordersService;
    }

    @RequestMapping("/")
    public ModelAndView listOrdersStatus(){
        ModelAndView modelAndView=new ModelAndView("orders-status-list");
        List<Orders> orderses=ordersService.listOrders();
        modelAndView.addObject("orderses",orderses);
        return modelAndView;
    }
}
