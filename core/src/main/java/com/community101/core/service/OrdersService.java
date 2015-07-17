package com.community101.core.service;

import com.community101.core.Orders;
import com.community101.core.dao.OrdersDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by jiaoming on 7/17/15.
 */
@Service
public class OrdersService {
    private OrdersDAO ordersDAO;

    @Autowired
    public OrdersService(OrdersDAO ordersDAO){
        this.ordersDAO=ordersDAO;
    }

    @Transactional
    public List<Orders> listOrders(){
        return ordersDAO.listOrders();
    }

}
