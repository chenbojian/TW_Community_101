package com.community101.core.daoTest;

import com.community101.core.Goods;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public class GoodsDAO {
    private SessionFactory sessionFactory;

    @Autowired
    public GoodsDAO(SessionFactory sessionFactory){
        this.sessionFactory=sessionFactory;
    }

    public List<Goods> goodsList() {
        return (List<Goods>) sessionFactory.getCurrentSession().createQuery("from Goods").list();
    }

    public void save(Goods goods) {
        sessionFactory.getCurrentSession().save(goods);
        
    }

    public Goods findGoodsById(long goodsId) {
        return (Goods) sessionFactory.getCurrentSession()
                .get(Goods.class, goodsId);
    }

    public void update(Goods goods) {
        sessionFactory.getCurrentSession()
                .update(goods);
    }

    public List<Goods> goodsWithoutCategory() {
        return sessionFactory.getCurrentSession().createQuery("from Goods g where g.category=null").list();
    }
}
