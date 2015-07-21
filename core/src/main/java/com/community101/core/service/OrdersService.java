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

    @Transactional
    public List<Orders> listNewOrders(){
        return ordersDAO.listNewOrders();
    }

    @Transactional
    public List<Orders> listDispatchingOrders(){
        return  ordersDAO.listDispatchingOrders();
    }

    @Transactional
    public List<Orders> listCompletedOrders(){
        return ordersDAO.listCompletedOrders();
    }

    @Transactional
    public List<Orders> listCancelOrders(){
        return ordersDAO.listCancelOrders();
    }

    @Transactional
    public Orders findOrdersById(long id){
        return ordersDAO.findOrdersById(id);
    }

    @Transactional
    public void dispatchOrder(long orderId) {
        ordersDAO.dispatchOrder(orderId);
    }

    @Transactional
    public void completeOrder(long orderId) {
        ordersDAO.completeOrder(orderId);
    }

    @Transactional
    public void cancelOrder(long orderId){
        ordersDAO.cancelOrder(orderId);
    }

    @Transactional
    public void addOrder(Orders order) {
        ordersDAO.addOrder(order);
    }

}
