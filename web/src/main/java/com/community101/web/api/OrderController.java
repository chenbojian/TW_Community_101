package com.community101.web.api;

import com.community101.core.Orders;
import com.community101.core.service.OrdersService;
import com.community101.web.DTO.OrderDetailDTO;
import com.community101.web.Mapper;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by chenjian on 7/17/15.
 */
@RestController
@RequestMapping("/api/order")
public class OrderController {
    private OrdersService ordersService;

    @Autowired
    public OrderController(OrdersService ordersService) {
        this.ordersService = ordersService;
    }

    @RequestMapping(value = "/detail")
    public OrderDetailDTO getOrderDetail(long orderId){
        Orders orders=ordersService.findOrdersById(orderId);
        return Mapper.makeOrderDetailDTO(orders);
    }

}
