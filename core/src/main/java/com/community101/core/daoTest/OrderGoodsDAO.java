package com.community101.core.daoTest;

import com.community101.core.OrderGoods;
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
public class OrderGoodsDAO {
    private SessionFactory sessionFactory;

    @Autowired
    public OrderGoodsDAO(SessionFactory sessionFactory){
        this.sessionFactory=sessionFactory;
    }

    public List<OrderGoods> orderGoodsList(){
        return (List<OrderGoods>)sessionFactory.getCurrentSession().createQuery("from OrderGoods").list();
    }

    public OrderGoods findOrderGoodsById(long id) {
        return (OrderGoods)sessionFactory.getCurrentSession().get(OrderGoods.class, id);
    }

    public void addOrderGoods(OrderGoods orderGoods) {
        sessionFactory.getCurrentSession().save(orderGoods);
    }
}
