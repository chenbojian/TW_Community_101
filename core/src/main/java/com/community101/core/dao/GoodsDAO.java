package com.community101.core.dao;

import com.community101.core.Goods;
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
public class GoodsDAO {
    private SessionFactory sessionFactory;

    @Autowired
    public GoodsDAO(SessionFactory sessionFactory){
        this.sessionFactory=sessionFactory;
    }

    public List<Goods> goodsList(){
        return (List<Goods>)sessionFactory.getCurrentSession().createQuery("from Goods").list();
    }
}
