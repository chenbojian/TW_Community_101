package com.community101.core.dao;

import com.community101.core.Category;
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
public class CategoryDAO {
    private SessionFactory sessionFactory;

    @Autowired
    public CategoryDAO(SessionFactory sessionFactory){
        this.sessionFactory=sessionFactory;
    }

    public List<Category> listCategory(){
        return sessionFactory.getCurrentSession().createQuery("from Category").list();
    }
}
