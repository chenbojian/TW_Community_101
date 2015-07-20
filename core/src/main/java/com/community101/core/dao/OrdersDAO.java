package com.community101.core.dao;

import com.community101.core.Orders;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by jiaoming on 7/17/15.
 */
@Repository
@Transactional
public class OrdersDAO {

    private SessionFactory sessionFactory;

    @Autowired
    public OrdersDAO(SessionFactory sessionFactory){
        this.sessionFactory=sessionFactory;
    }

    public List<Orders> listOrders(){
        return (List<Orders>)sessionFactory.getCurrentSession()
                .createQuery("from Orders")
                .list();
    }

    public List<Orders> listNewOrders(){
        return (List<Orders>)sessionFactory.getCurrentSession().createQuery("from Orders where status='new'").list();
    }

    public List<Orders> listDispatchingOrders(){
        return (List<Orders>)sessionFactory.getCurrentSession().createQuery("from Orders where status='dispatching'").list();
    }

    public List<Orders> listCompletedOrders(){
        return (List<Orders>)sessionFactory.getCurrentSession().createQuery("from Orders where status='completed'").list();
    }

    public List<Orders> listCancelOrders(){
        return (List<Orders>)sessionFactory.getCurrentSession().createQuery("from Orders where status='cancel'").list();
    }

    public Orders findOrdersById(long id){
        return (Orders)sessionFactory.getCurrentSession().get(Orders.class, id);
    }

    public void dispatchOrder(long orderId) {
        Orders order = findOrdersById(orderId);
        order.setStatus("dispatching");
        sessionFactory.getCurrentSession().update(order);
    }

    public void completeOrder(long orderId) {
        Orders order = findOrdersById(orderId);
        order.setStatus("completed");
        sessionFactory.getCurrentSession().update(order);
    }

    public void cancelOrder(long orderId){
        Orders order = findOrdersById(orderId);
        order.setStatus("cancel");
        sessionFactory.getCurrentSession().update(order);
    }
}
