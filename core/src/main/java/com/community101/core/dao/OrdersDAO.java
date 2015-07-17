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

}
