package com.community101.core.dao;

import com.community101.core.User;
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
public class UserDAO {
    private SessionFactory sessionFactory;

    @Autowired
    public UserDAO(SessionFactory sessionFactory){
        this.sessionFactory=sessionFactory;
    }

    public List<User> listUsers(){
        return (List<User>)sessionFactory.getCurrentSession().createQuery("from User").list();
    }
}
