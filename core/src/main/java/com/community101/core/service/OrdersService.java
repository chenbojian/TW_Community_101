package com.community101.core.service;

import com.community101.core.OrderGoods;
import com.community101.core.Orders;
import com.community101.core.dao.OrderGoodsDAO;
import com.community101.core.dao.OrdersDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by jiaoming on 7/17/15.
 */
@Service
public class OrdersService {
    static private final Lock lock = new ReentrantLock();

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
    public boolean dispatchOrder(long orderId) {
        boolean isDone = false;
        try {
            lock.tryLock();
            if (ordersDAO.findOrdersById(orderId).getStatus().equals("new")) {
                ordersDAO.dispatchOrder(orderId);
                isDone = true;
            }
        }
        finally {
            lock.unlock();
        }
        return isDone;
    }

    @Transactional
    public boolean completeOrder(long orderId) {
        boolean isDone = false;
        try {
            lock.tryLock();
            if (ordersDAO.findOrdersById(orderId).getStatus().equals("dispatching")) {
                ordersDAO.completeOrder(orderId);
                isDone = true;
            }
        }
        finally {
            lock.unlock();
        }
        return isDone;
    }

    @Transactional
    public boolean cancelOrder(long orderId){
        boolean isDone = false;
        try {
            lock.tryLock();
            if (!ordersDAO.findOrdersById(orderId).getStatus().equals("cancel")) {
                ordersDAO.cancelOrder(orderId);
                isDone = true;
            }
        }
        finally {
            lock.unlock();
        }
        return isDone;
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

    @Transactional
    public boolean hasNewOrders(){
        return ordersDAO.hasNewOrders();
    }

    @Transactional
    public void cleanNewOrdersTable(){
        ordersDAO.cleanNewOrdersTable();
    }
}
