package com.community101.core.service;

import com.community101.core.OrderGoods;
import com.community101.core.Orders;
import com.community101.core.daoTest.OrderGoodsDAO;
import com.community101.core.daoTest.OrdersDAO;
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
    private OrderGoodsDAO orderGoodsDAO;

    @Autowired
    public OrdersService(OrdersDAO ordersDAO,
                         OrderGoodsDAO orderGoodsDAO){
        this.ordersDAO=ordersDAO;
        this.orderGoodsDAO = orderGoodsDAO;
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
        for (OrderGoods orderGoods : order.getOrderGoodses()) {
            orderGoods.setOrders(order);
            orderGoodsDAO.addOrderGoods(orderGoods);
        }
    }

    @Transactional
    public OrderGoods findOrderGoodsById(long id) {
        return orderGoodsDAO.findOrderGoodsById(id);
    }
}
